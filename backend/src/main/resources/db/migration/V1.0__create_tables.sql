
create table user (
    id int not null auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null unique,
    phone_number varchar(20) not null,
    gender varchar(30) not null,
    date_of_birth date not null,
    user_name varchar(30) not null unique,
    password varchar(100) not null,
    date_created datetime not null,
    role varchar(10) not null;
    primary key (id)
);

create table doctor_profile (
  id int not null AUTO_INCREMENT,
  experience int not null,
  specialization varchar(50) not null,
  qualification varchar(50) not null,
  reg_no varchar(10) not null,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  date_created datetime not null,
  primary key (id),
  user_id int not null,
  constraint fk_doctor_user foreign key (user_id) references user(id)
);

create table patient_profile (
  id int not null AUTO_INCREMENT,
  address varchar(100) not null,
  allergies text,
  additional_info text,
  family_history text,
  date_created datetime not null,
  insurance_number varchar(20) unique not null,
  primary key (id),
  user_id int not null,
  constraint fk_patient_user foreign key (user_id) references user(id)
);

create table ehr (
    ehr_id int not null auto_increment,
    patient_id int not null,
    date date not null,
    symptom varchar(255),
    diagnosis varchar(255),
    treatment varchar(255),
    notes text,
    primary key (ehr_id),
    foreign key (patient_id) references user(id)
);

create table prescription (
  id int not null auto_increment,
  patient_id int not null,
  doctor_id int not null,
  medication varchar(255) not null,
  dosage varchar(50),
  instructions varchar(255),
  date_prescribed date not null,
  primary key (id),
  foreign key (patient_id) references user(id),
  foreign key (doctor_id) references user(id)
);

CREATE TABLE vaccine (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  manufacturer VARCHAR(255) NOT NULL,
  description TEXT,
  next_dosage   int,
  age int,
  FOREIGN KEY (next_dosage) REFERENCES vaccine(id),
  PRIMARY KEY (id)
);

CREATE TABLE appointment (
  id INT NOT NULL AUTO_INCREMENT,
  doctor_id INT,
  patient_id INT,
  appointment_type ENUM('doctor', 'vaccination') NOT NULL,
  date DATE,
  time TIME,
  reason_for_visit TEXT,
  vaccine_id int,
  dose_number INT,
  next_dose_date DATE,
  status VARCHAR(10) not null,
  PRIMARY KEY (id),
  FOREIGN KEY (doctor_id) REFERENCES user(id),
  FOREIGN KEY (patient_id) REFERENCES user(id),
  FOREIGN KEY (vaccine_id) REFERENCES vaccine(id)
);

CREATE TABLE patient_vaccination (
  id INT NOT NULL AUTO_INCREMENT,
  patient_id INT NOT NULL,
  doctor_id INT NOT NULL,
  vaccine_id int NOT NULL,
  dose_number INT NOT NULL,
  date_administered DATE NOT NULL,
  next_dose_date DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES user(id),
  FOREIGN KEY (vaccine_id) REFERENCES vaccine(id)
);