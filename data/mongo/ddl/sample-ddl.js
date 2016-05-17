use agillaire

db.createCollection("patient");
db.patient.ensureIndex({ userId: 1 }, { unique: true });