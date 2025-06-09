![WhatsApp Image 2025-06-05 at 10 13 11_9cff5f1c](https://github.com/user-attachments/assets/de0a70e8-ffbd-4234-a951-6cef0050695e)


🏥 Remote Patient Monitoring System
This project is a Remote Patient Monitoring System — a desktop app that helps doctors and patients stay connected online, even when they are far from each other. It was made as part of an Object-Oriented Programming (OOP) course.

💡 What is this project?
Imagine a hospital, but online. Patients can:

Schedule appointments

Talk to doctors on Google Meet

Upload their health data (like heart rate or blood pressure)

See graphs of their health progress

Send emergency emails if something goes wrong

Doctors can:

View patient records

Monitor vitals

Get emergency alerts via email

Keep everything organized and safe

🛠️ Tech Used
Part	Technology
Backend	Java (OOP)
Frontend	JavaFX
Database	MySQL
Emails	Jakarta Mail API

🚀 Main Features
✅ Appointment Scheduling
Patients can choose a date and time to book an appointment with a doctor.

📞 Online Google Meet Calling
Appointments automatically include a link to a Google Meet video call.

📊 Upload & Monitor Vitals
Patients can upload their health vitals (like temperature, oxygen level, etc.).
Doctors can see this data in a graph format.

📧 Emergency Email Alerts
If a patient's vitals are too high or too low, the system sends an emergency email to the doctor using jakarta.mail.

🚨 Panic Button
A patient can click a button if they feel unwell — the system sends an alert email immediately.

👩‍💻 How to Run It
Database Setup

Open MySQL

Import the provided .sql file to create the database and tables

Update your database credentials in the Java backend code

Backend

Written in Java using OOP concepts

Handles all business logic (appointments, vitals, emails, etc.)

Frontend

Created using JavaFX

User-friendly interface for both doctors and patients

Email Configuration

Jakarta Mail is used for sending emails

You need to allow access to less secure apps or generate an app password for Gmail

🧠 Concepts Covered (OOP)
Classes & Objects

Inheritance

Encapsulation

Abstraction

Polymorphism

Exception Handling

File I/O

GUI development

Java-MySQL connectivity

Real-world system design
