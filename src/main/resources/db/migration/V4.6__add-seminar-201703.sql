INSERT INTO seminar(seminar_id, seminar_date, seminar_name) VALUES (0x00000000000000000000000020170328, '2017-03-28', 'JSUG勉強会 2017年その3 ～ ドメイン駆動設計 powered by Spring');

--

INSERT INTO session (session_id, session_name, seminar_id) VALUES (0x10000000000000000000000020170328, '1. ドメインロジックに集中せよ', 0x00000000000000000000000020170328);
INSERT INTO session (session_id, session_name, seminar_id) VALUES (0x20000000000000000000000020170328, '2. トークセッション「ドメインロジックの実装方法の選択」', 0x00000000000000000000000020170328);

--

INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x10000000000000000000000020170328, '増田　亨');
INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x20000000000000000000000020170328, '長谷川 裕一');
INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x20000000000000000000000020170328, '増田 亨');
INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x20000000000000000000000020170328, '綿引 琢磨');
INSERT INTO session_speaker_display_names (session_id, speaker_display_name) VALUES (0x20000000000000000000000020170328, '池谷 智行');
