const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ADMIN_UID = "admin_samarth";
const ADMIN_EMAIL = "12345samarthpro@gmail.com";
const VISITOR_UID = "visitor_alice";
const OTHER_UID = "visitor_bob";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);
const pastTimestamp = () => new Date(Date.now() - 2000);

function validPlacePayload(ownerId) {
  const ts = pastTimestamp();
  return {
    name: "Bara Imambara",
    category: "Heritage & Monuments",
    locality: "Machchhi Bhavan, Old Lucknow",
    address: "Husainabad Trust Road, Machchhi Bhavan, Lucknow 226003",
    timings: "6:00 AM - 5:00 PM (Daily)",
    closedOn: "Open all days",
    ticketPrice: "Rs 50 Indians | Rs 500 Foreigners",
    bestTimeToVisit: "Oct to Mar, Morning",
    description: "Iconic 1784 Awadhi monument with the famous Bhool Bhulaiya labyrinth.",
    highlights: ["Bhool Bhulaiya", "Asfi Mosque", "Shahi Baoli"],
    photoUrls: ["https://upload.wikimedia.org/wikipedia/commons/e/ea/Bara_Imambara_Lucknow.jpg"],
    reviewVideoUrl: "https://www.youtube.com/results?search_query=Bara+Imambara+Lucknow+review+tour",
    reviewVideoTitle: "Bara Imambara & Bhool Bhulaiya Complete Tour",
    reviewSummary: "Must-visit architectural marvel. Hire an official guide for Bhool Bhulaiya.",
    rating: 4.8,
    reviewCount: 42500,
    latitude: 26.8691,
    longitude: 80.9127,
    isPublished: true,
    isFeatured: true,
    ownerId,
    updatedByEmail: ADMIN_EMAIL,
    createdAt: ts,
    updatedAt: ts,
  };
}

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

test("Unauthenticated user: cannot list or read places", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("places").where("isPublished", "==", true).get());
  await assertFails(unauthDb.collection("places").doc("bara_imambara").get());
});

test("Verified admin: can create and update global place", async () => {
  const adminDb = testEnv.authenticatedContext(ADMIN_UID, {
    email: ADMIN_EMAIL,
    email_verified: true,
  }).firestore();

  const placeRef = adminDb.collection("places").doc("bara_imambara");
  await assertSucceeds(placeRef.set(validPlacePayload(ADMIN_UID)));
  await assertSucceeds(
    placeRef.update({
      ticketPrice: "Rs 60 Indians | Rs 500 Foreigners",
      updatedAt: pastTimestamp(),
    })
  );
});

test("Email spoofing attack: unverified admin email is rejected", async () => {
  const spoofDb = testEnv.authenticatedContext("spoof_uid", {
    email: ADMIN_EMAIL,
    email_verified: false,
  }).firestore();
  await assertFails(
    spoofDb.collection("places").doc("bara_imambara").set(validPlacePayload("spoof_uid"))
  );
});

test("Regular signed-in visitor: can query published places, cannot create/update global places", async () => {
  const adminDb = testEnv.authenticatedContext(ADMIN_UID, {
    email: ADMIN_EMAIL,
    email_verified: true,
  }).firestore();
  await assertSucceeds(adminDb.collection("places").doc("bara_imambara").set(validPlacePayload(ADMIN_UID)));

  const visitorDb = testEnv.authenticatedContext(VISITOR_UID, {
    email: "alice@example.com",
    email_verified: true,
  }).firestore();

  await assertSucceeds(visitorDb.collection("places").where("isPublished", "==", true).get());
  await assertFails(visitorDb.collection("places").get()); // unfiltered list fails
  await assertFails(
    visitorDb.collection("places").doc("new_place").set(validPlacePayload(VISITOR_UID))
  );
});

test("Shadow update test: ghost field rejected on place create and saved_place", async () => {
  const adminDb = testEnv.authenticatedContext(ADMIN_UID, {
    email: ADMIN_EMAIL,
    email_verified: true,
  }).firestore();
  const payloadWithGhost = { ...validPlacePayload(ADMIN_UID), isVerifiedByGhost: true };
  await assertFails(adminDb.collection("places").doc("bara_imambara").set(payloadWithGhost));
});

test("User isolation: visitor can manage own saved_places and custom_places, cannot read/write another user's", async () => {
  const aliceDb = testEnv.authenticatedContext(VISITOR_UID).firestore();
  const bobDb = testEnv.authenticatedContext(OTHER_UID).firestore();
  const ts = pastTimestamp();

  const savedRef = aliceDb.collection("users").doc(VISITOR_UID).collection("saved_places").doc("bara_imambara");
  await assertSucceeds(
    savedRef.set({
      placeId: "bara_imambara",
      isBookmarked: true,
      isFavorite: true,
      isVisited: false,
      personalNote: "Go at 8 AM",
      userRating: 5.0,
      createdAt: ts,
      updatedAt: ts,
    })
  );
  await assertSucceeds(
    savedRef.update({
      isFavorite: false,
      updatedAt: pastTimestamp(),
    })
  );
  await assertFails(
    savedRef.update({
      isFavorite: "yes",
      updatedAt: pastTimestamp(),
    })
  );

  const customRef = aliceDb.collection("users").doc(VISITOR_UID).collection("custom_places").doc("my_chai_spot");
  await assertSucceeds(customRef.set(validPlacePayload(VISITOR_UID)));

  await assertFails(
    bobDb.collection("users").doc(VISITOR_UID).collection("saved_places").doc("bara_imambara").get()
  );
  await assertFails(
    bobDb.collection("users").doc(VISITOR_UID).collection("saved_places").doc("bara_imambara").set({
      placeId: "bara_imambara",
      isBookmarked: false,
      isFavorite: true,
      isVisited: false,
      personalNote: "",
      userRating: 0,
      createdAt: ts,
      updatedAt: ts,
    })
  );
  await assertFails(
    bobDb.collection("users").doc(VISITOR_UID).collection("custom_places").get()
  );
});

test("Privilege escalation: user cannot write to /admins collection", async () => {
  const visitorDb = testEnv.authenticatedContext(VISITOR_UID).firestore();
  await assertFails(visitorDb.collection("admins").doc(VISITOR_UID).set({ role: "admin" }));
});
