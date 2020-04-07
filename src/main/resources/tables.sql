CREATE TABLE users(
    uuid UUID PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    phoneNumber VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE wallets(
    uuid UUID PRIMARY KEY,
    currency VARCHAR NOT NULL,
    amount NUMERIC NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
