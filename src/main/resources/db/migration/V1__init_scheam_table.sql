CREATE TABLE "book" (
	"id" bigserial NOT NULL,
	"created_at" timestamptz DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamptz DEFAULT CURRENT_TIMESTAMP,
	"deleted_at" timestamptz,
	"name" varchar,
	"quantity" int,
	"price" int,
	CONSTRAINT "book_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "customer" (
	"id" bigserial NOT NULL,
	"created_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
	"deleted_at" TIMESTAMP,
	"customer_name" varchar,
	"phone" varchar,
	"email" varchar,
	CONSTRAINT "customer_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "transaction" (
	"id" bigserial NOT NULL,
	"created_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
	"deleted_at" TIMESTAMP ,
	"loan_date" TIMESTAMP,
	"returned_date" TIMESTAMP,
	"customer_id" bigint REFERENCES customer(id) NOT NULL,
	"customer_name" VARCHAR,
	"total_quantity" bigint,
	"total_fee" bigint,
	"fine_fee" bigint,
	"status" VARCHAR,
	CONSTRAINT "transaction_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "detail_transaction" (
	"id" bigserial NOT NULL,
	"transaction_id" bigint REFERENCES transaction(id) NOT NULL,
	"created_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
	"deleted_at" TIMESTAMP ,
	"book_id" bigint REFERENCES book(id) NOT NULL,
	"detail_quantity" bigint,
	CONSTRAINT "transaction_detail_pkey" PRIMARY KEY ("id")
);