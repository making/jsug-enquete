INSERT INTO seminar(seminar_id, seminar_date, seminar_name) VALUES (0x00000000000000000000000020170227, '2017-02-27', 'JSUG勉強会 2017年その2 ～ 脱Spring Boot初心者');

--

INSERT INTO session (session_id, session_name, seminar_id) VALUES (0x10000000000000000000000020170227, '1. Spring for Spring Boot -Spring Bootユーザーが知るべきSpringの基礎知識-', 0x00000000000000000000000020170227);
INSERT INTO session (session_id, session_name, seminar_id) VALUES (0x20000000000000000000000020170227, '2. 実例で学ぶ、明日から使えるSpring Boot Tips', 0x00000000000000000000000020170227);

--

INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x10000000000000000000000020170227, '多田 真敏');
INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x20000000000000000000000020170227, '槙 俊明');

--

INSERT INTO session_speakers (session_id, speaker) VALUES (0x10000000000000000000000020170227, 'MasatoshiTada');
INSERT INTO session_speakers (session_id, speaker) VALUES (0x20000000000000000000000020170227, 'making');