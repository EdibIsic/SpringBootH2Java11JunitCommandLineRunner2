CREATE TABLE IF NOT EXISTS EventDataDb(
   id VARCHAR (45),
   duration VARCHAR (45),
   type VARCHAR (45),
   host VARCHAR (45),
   alert BOOLEAN DEFAULT FALSE,
   PRIMARY KEY (ID)
);