CREATE TABLE USERS
(
  ID          NUMBER          NOT NULL
, USERNAME    VARCHAR2(100)   NOT NULL
, PWD         VARCHAR2(512)
, NAME        VARCHAR2(100)
, EMAIL       VARCHAR2(100)
, CONSTRAINT  USERS_PK          PRIMARY KEY (ID)
, CONSTRAINT  USERS_UK_USERNAME UNIQUE      (USERNAME)
, CONSTRAINT  USERS_UK_EMAIL    UNIQUE      (EMAIL)
);

CREATE SEQUENCE USERS_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TRIGGER USERS_TRG 
BEFORE INSERT ON USERS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT USERS_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

CREATE TABLE ITEMS 
(
  ID          NUMBER          NOT NULL 
, ITEMNAME    VARCHAR2(1000)  NOT NULL 
, PRICE       NUMBER 
, CONSTRAINT  ITEMS_PK  PRIMARY KEY (ID)
, CONSTRAINT  ITEMS_UK1 UNIQUE      (ITEMNAME)
);

CREATE SEQUENCE ITEMS_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TRIGGER ITEMS_TRG 
BEFORE INSERT ON ITEMS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT ITEMS_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

CREATE TABLE EXPENSE_LOGS 
(
  ID        NUMBER      NOT NULL
, DATETIME  DATE
, ITEM_ID   NUMBER      NOT NULL
, QUANTITY  NUMBER      
, AMOUNT    NUMBER
, CONSTRAINT EXPENSE_LOGS_PK  PRIMARY KEY (ID)
, CONSTRAINT EXPENSE_LOGS_FK1 FOREIGN KEY (ITEM_ID) REFERENCES ITEMS (ID)
);

CREATE SEQUENCE EXP_LOG_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TRIGGER EXP_LOG_TRG 
BEFORE INSERT ON EXPENSE_LOGS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT EXP_LOG_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

CREATE TABLE SHARE_DETAILS
(
, ID	            NUMBER    NOT NULL
, EXP_LOG_ID        NUMBER    NOT NULL
, USER_ID           NUMBER    NOT NULL
, SHARE_MULTIPLIER  NUMBER
, CONSTRAINT SHARE_DETAILS_PK       PRIMARY KEY (EXP_LOG_ID,USER_ID)
, CONSTRAINT SHARE_DETAILS_FK_LOG   FOREIGN KEY (EXP_LOG_ID)  REFERENCES EXPENSE_LOGS (ID)
, CONSTRAINT SHARE_DETAILS_FK_USERS FOREIGN KEY (USER_ID)     REFERENCES USERS (ID)
);

CREATE SEQUENCE SHARE_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TRIGGER SHARE_TRG 
BEFORE INSERT ON SHARE_DETAILS
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT SHARE_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/

CREATE TABLE CONTRIBUTION
(
  ID        NUMBER    NOT NULL
, DATETIME  DATE
, ITEM_ID   NUMBER    NOT NULL
, USER_ID   NUMBER    NOT NULL
, AMOUNT    NUMBER
, CONSTRAINT CONTRI_PK PRIMARY KEY (ID)
, CONSTRAINT CONTRI_FK_ITEMS FOREIGN KEY (ITEM_ID) REFERENCES ITEMS (ID)
, CONSTRAINT CONTRI_FK_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

CREATE SEQUENCE CONTRI_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TRIGGER CONTRI_TRG 
BEFORE INSERT ON CONTRIBUTION 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT CONTRI_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/