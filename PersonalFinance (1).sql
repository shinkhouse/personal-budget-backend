CREATE TABLE "categories" (
  "id" integer UNIQUE PRIMARY KEY,
  "description" varchar,
  "isDefault" boolean,
  "type" varchar,
  "createdOn" timestamp,
  "createdBy" varchar,
  "updatedOn" timestamp,
  "updatedBy" varchar
);

CREATE TABLE "expenses" (
  "id" integer UNIQUE PRIMARY KEY,
  "userId" integer,
  "description" varchar,
  "dated" timestamp,
  "amount" number,
  "paymentType" varchar,
  "category" varchar,
  "tag" varchar,
  "subDescription" varchar,
  "createdOn" timestamp,
  "createdBy" varchar,
  "updatedOn" timestamp,
  "updatedBy" varchar
);

CREATE TABLE "income" (
  "id" integer UNIQUE PRIMARY KEY,
  "userId" integer,
  "description" varchar,
  "amount" number,
  "dated" timestamp,
  "paymentTypeId" integer,
  "tag" varchar,
  "subDescription" varchar,
  "createdOn" timestamp,
  "createdBy" varchar,
  "updatedOn" timestamp,
  "updatedBy" varchar
);

CREATE TABLE "payment_types" (
  "id" integer UNIQUE PRIMARY KEY,
  "description" varchar,
  "isDefault" boolean,
  "type" varchar,
  "createdOn" timestamp,
  "createdBy" varchar,
  "updatedOn" timestamp,
  "updatedBy" varchar
);

CREATE TABLE "users" (
  "id" integer UNIQUE PRIMARY KEY,
  "userId" varchar,
  "email" varchar,
  "password" varchar,
  "fullName" varchar,
  "isLoggedIn" boolean,
  "lastLoggedOn" timestamp,
  "createdOn" timestamp,
  "updatedOn" timestamp,
  "createdBy" varchar,
  "updatedBy" varchar
);

CREATE TABLE "currency" (
  "id" integer UNIQUE PRIMARY KEY,
  "name" varchar,
  "createdOn" timestamp,
  "updatedOn" timestamp
);

CREATE TABLE "security_questions" (
  "id" integer UNIQUE PRIMARY KEY,
  "question" varchar(255),
  "createdOn" timestamp,
  "updatedOn" timestamp
);

CREATE TABLE "security_question_answers" (
  "id" integer UNIQUE PRIMARY KEY,
  "questionId" integer,
  "answer" varchar,
  "createdOn" timestamp,
  "updatedOn" timestamp
);

CREATE TABLE "preferences" (
  "id" integer UNIQUE PRIMARY KEY,
  "userId" integer,
  "securityQuestionId" integer,
  "currencyId" integer,
  "createdOn" timestamp,
  "updatedOn" timestamp
);

ALTER TABLE "currency" ADD FOREIGN KEY ("id") REFERENCES "preferences" ("currencyId");

ALTER TABLE "preferences" ADD FOREIGN KEY ("securityQuestionId") REFERENCES "security_question_answers" ("id");

ALTER TABLE "security_question_answers" ADD FOREIGN KEY ("questionId") REFERENCES "security_questions" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "preferences" ("userId");

ALTER TABLE "expenses" ADD FOREIGN KEY ("userId") REFERENCES "users" ("id");

ALTER TABLE "income" ADD FOREIGN KEY ("userId") REFERENCES "users" ("id");

ALTER TABLE "categories" ADD FOREIGN KEY ("createdBy") REFERENCES "users" ("id");

ALTER TABLE "categories" ADD FOREIGN KEY ("updatedBy") REFERENCES "users" ("id");

ALTER TABLE "payment_types" ADD FOREIGN KEY ("createdBy") REFERENCES "users" ("id");

ALTER TABLE "payment_types" ADD FOREIGN KEY ("updatedBy") REFERENCES "users" ("id");

ALTER TABLE "income" ADD FOREIGN KEY ("paymentTypeId") REFERENCES "payment_types" ("id");

ALTER TABLE "income" ADD FOREIGN KEY ("createdBy") REFERENCES "users" ("id");

ALTER TABLE "income" ADD FOREIGN KEY ("updatedBy") REFERENCES "users" ("id");
