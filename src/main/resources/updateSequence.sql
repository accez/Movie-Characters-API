-- SELECT setval(hibernate_sequence('franchise','id'),coalesce (max(id)+1,1),false ) FROM franchise

-- select setval('hibernate_sequence', (SELECT MAX(id) FROM franchise));
select setval('hibernate_sequence', (SELECT MAX(id) FROM movie));
-- select setval('hibernate_sequence', (SELECT MAX(id) FROM movie_character));
