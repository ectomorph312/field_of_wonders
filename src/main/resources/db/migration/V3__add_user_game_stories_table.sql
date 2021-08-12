CREATE TABLE user_game_stories
(
    id          BIGSERIAL PRIMARY KEY            NOT NULL,
    question_id BIGINT REFERENCES questions (id) NOT NULL,
    user_id     BIGINT REFERENCES users (id)     NOT NULL
);