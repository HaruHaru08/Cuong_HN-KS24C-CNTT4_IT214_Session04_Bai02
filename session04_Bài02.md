# session04_Bài02 — Xây dựng API cơ bản cho từng Microservice

## Cấu trúc dự án (Multi-module Gradle)

5 service được đóng gói thành các module con của một dự án Gradle duy nhất:

```text
session04_Bài02/                         # Root project
├── build.gradle                         # Cấu hình chung cho tất cả subproject
├── settings.gradle                      # Khai báo 5 module con
├── gradlew / gradlew.bat
│
├── patient-service/        (port 8081)  # Quản lý bệnh nhân
├── doctor-service/         (port 8082)  # Quản lý bác sĩ
├── appointment-service/    (port 8083)  # Quản lý lịch hẹn khám
├── medical-record-service/ (port 8084)  # Quản lý hồ sơ bệnh án
└── pharmacy-service/       (port 8085)  # Quản lý thuốc
```

Mỗi service có cấu trúc phân tầng chuẩn:
```text
{service}/src/main/java/com/medicare/{domain}/
├── {Domain}ServiceApplication.java   # Main class
├── controller/                        # Tiếp nhận HTTP request
├── service/                           # Xử lý logic nghiệp vụ
├── repository/                        # Tương tác database JPA
├── model/                             # Entity ánh xạ bảng DB
└── dto/                               # DTO trao đổi dữ liệu giữa tầng
```

---

## Dependency dùng chung (Root build.gradle)

- `spring-boot-starter-web` — REST API
- `spring-boot-starter-data-jpa` — ORM với MySQL
- `mysql-connector-j` — MySQL Driver
- `lombok` — Giảm boilerplate code

---

## Danh sách API CRUD từng Service

### 1. patient-service (port 8081) — Database: `medicare_patient_db`

| Method | Endpoint               | Mô tả                        |
|--------|------------------------|------------------------------|
| GET    | /api/patients          | Lấy danh sách bệnh nhân      |
| GET    | /api/patients/{id}     | Lấy bệnh nhân theo ID        |
| POST   | /api/patients          | Tạo bệnh nhân mới            |
| PUT    | /api/patients/{id}     | Cập nhật thông tin bệnh nhân |
| DELETE | /api/patients/{id}     | Xóa bệnh nhân                |

**JSON body mẫu (POST /api/patients):**
```json
{
  "fullName": "Nguyễn Văn A",
  "dateOfBirth": "1990-05-15",
  "gender": "MALE",
  "phone": "0901234567",
  "address": "123 Trần Hưng Đạo, Hà Nội",
  "insuranceId": "BH123456789"
}
```

### 2. doctor-service (port 8082) — Database: `medicare_doctor_db`

| Method | Endpoint             | Mô tả                   |
|--------|----------------------|-------------------------|
| GET    | /api/doctors         | Lấy danh sách bác sĩ    |
| GET    | /api/doctors/{id}    | Lấy bác sĩ theo ID      |
| POST   | /api/doctors         | Tạo bác sĩ mới          |
| PUT    | /api/doctors/{id}    | Cập nhật thông tin bác sĩ|
| DELETE | /api/doctors/{id}    | Xóa bác sĩ              |

**JSON body mẫu (POST /api/doctors):**
```json
{
  "fullName": "Trần Thị B",
  "specialization": "Tim mạch",
  "licenseNumber": "LS001234",
  "phone": "0912345678",
  "email": "tranthib@medicare.vn",
  "department": "Khoa Tim mạch"
}
```

### 3. appointment-service (port 8083) — Database: `medicare_appointment_db`

| Method | Endpoint                  | Mô tả                   |
|--------|---------------------------|-------------------------|
| GET    | /api/appointments         | Lấy danh sách lịch hẹn  |
| GET    | /api/appointments/{id}    | Lấy lịch hẹn theo ID    |
| POST   | /api/appointments         | Tạo lịch hẹn mới        |
| PUT    | /api/appointments/{id}    | Cập nhật lịch hẹn       |
| DELETE | /api/appointments/{id}    | Xóa lịch hẹn            |

**JSON body mẫu (POST /api/appointments):**
```json
{
  "patientId": 1,
  "doctorId": 1,
  "appointmentTime": "2026-09-10T09:00:00",
  "reason": "Khám tim định kỳ",
  "status": "SCHEDULED"
}
```

### 4. medical-record-service (port 8084) — Database: `medicare_medical_record_db`

| Method | Endpoint                   | Mô tả                        |
|--------|----------------------------|------------------------------|
| GET    | /api/medical-records       | Lấy danh sách hồ sơ bệnh án  |
| GET    | /api/medical-records/{id}  | Lấy hồ sơ theo ID            |
| POST   | /api/medical-records       | Tạo hồ sơ bệnh án mới        |
| PUT    | /api/medical-records/{id}  | Cập nhật hồ sơ bệnh án       |
| DELETE | /api/medical-records/{id}  | Xóa hồ sơ bệnh án            |

**JSON body mẫu (POST /api/medical-records):**
```json
{
  "patientId": 1,
  "doctorId": 1,
  "recordDate": "2026-09-08",
  "diagnosis": "Rối loạn nhịp tim nhẹ",
  "treatment": "Thuốc điều hòa nhịp tim, nghỉ ngơi",
  "notes": "Tái khám sau 2 tuần"
}
```

### 5. pharmacy-service (port 8085) — Database: `medicare_pharmacy_db`

| Method | Endpoint               | Mô tả                   |
|--------|------------------------|-------------------------|
| GET    | /api/medicines         | Lấy danh sách thuốc     |
| GET    | /api/medicines/{id}    | Lấy thuốc theo ID       |
| POST   | /api/medicines         | Thêm thuốc mới          |
| PUT    | /api/medicines/{id}    | Cập nhật thông tin thuốc|
| DELETE | /api/medicines/{id}    | Xóa thuốc               |

**JSON body mẫu (POST /api/medicines):**
```json
{
  "name": "Aspirin 100mg",
  "activeIngredient": "Acetylsalicylic acid",
  "unit": "viên",
  "stockQuantity": 500,
  "price": 2500.0,
  "manufacturer": "Bayer"
}
```

---

## Kiểm thử API với Postman

File collection mẫu đã được tạo sẵn tại: [`medicare_postman_collection.json`](file:///e:/IT214_BTVN/session04_B%C3%A0i02/medicare_postman_collection.json)

Bạn chỉ cần:
1. Mở Postman -> Chọn **Import**.
2. Chọn file `medicare_postman_collection.json`.
3. Collection bao gồm đầy đủ 25 requests (5 requests CRUD cho mỗi service trong 5 dịch vụ).

---

## Hướng dẫn cấu hình MySQL trước khi chạy

Mỗi service sử dụng tham số `createDatabaseIfNotExist=true` trong JDBC URL nên MySQL sẽ tự tạo database. Chỉ cần đảm bảo MySQL đang chạy và đúng thông tin kết nối trong `application.yml` của từng service (`username: root`, `password: root`).

---

## Lệnh build và chạy từng service

**Build toàn bộ project:**
```bash
./gradlew build
```

**Chạy riêng từng service:**
```bash
./gradlew :patient-service:bootRun
./gradlew :doctor-service:bootRun
./gradlew :appointment-service:bootRun
./gradlew :medical-record-service:bootRun
./gradlew :pharmacy-service:bootRun
```


