# Quản Lý Bất Động Sản

Dự án Quản Lý Bất Động Sản là một ứng dụng web cho phép người dùng thêm, sửa, xóa các bất động sản và giao bất động sản cho nhân viên quản lý. Ứng dụng sử dụng Spring Boot cho backend và triển khai các chức năng qua API cũng như giao diện người dùng qua Model và View.

## Các Tính Năng

1. **Thêm bất động sản**: Cho phép thêm thông tin chi tiết về một bất động sản mới vào hệ thống.
2. **Sửa bất động sản**: Cho phép chỉnh sửa các thông tin của bất động sản đã tồn tại.
3. **Xóa bất động sản**: Cho phép xóa bất động sản khỏi hệ thống.
4. **Giao bất động sản cho nhân viên quản lý**: Cho phép giao các bất động sản cho các nhân viên quản lý cụ thể.
5. **Tìm kiếm theo 16 trường**: Hỗ trợ tìm kiếm và lọc bất động sản theo 16 trường khác nhau (ví dụ: tên tòa nhà, địa chỉ, diện tích, giá thuê, v.v.).
6. **Sử dụng `Enum`**: Một số trường trong dự án sử dụng `Enum` để quản lý các giá trị có sẵn như loại bất động sản, trạng thái tòa nhà, v.v.
7. **Sử dụng `Builder Pattern`**: Các đối tượng như `BuildingDTO` được xây dựng bằng `Builder Pattern` để đảm bảo tính mở rộng và dễ bảo trì và một số các kỹ thuật khác.

## Cấu Trúc Dự Án

- **Controller**: Chịu trách nhiệm xử lý các yêu cầu từ client và trả về các Model và View. Controller không xử lý các nghiệp vụ mà chỉ giao tiếp với các API.
  - **Model and View**: Các Controller sử dụng `Model` để thêm dữ liệu vào view và hiển thị giao diện cho người dùng.
  
- **API**: Các chức năng chính (Thêm, Sửa, Xóa, Tìm kiếm) được xử lý qua các API, sử dụng các RESTful endpoints. API nhận các yêu cầu HTTP từ frontend và trả về dữ liệu dưới dạng JSON.
  
- **Service**: Chịu trách nhiệm xử lý các nghiệp vụ chính của ứng dụng, bao gồm việc gọi các phương thức từ Repository để thao tác với dữ liệu.
  
- **Repository**: Quản lý các thao tác cơ sở dữ liệu với JPA. Bao gồm các truy vấn SQL tùy chỉnh (Custom Query) như tìm kiếm bất động sản theo nhiều trường khác nhau.
  
- **Entity**: Các lớp thực thể ánh xạ với bảng trong cơ sở dữ liệu.
  
- **DTO (Data Transfer Object)**: Dùng để chuyển dữ liệu giữa các tầng của ứng dụng và giữa backend và frontend mà không lộ thông tin nội bộ của hệ thống.

![Image](https://github.com/user-attachments/assets/ee99d004-a7bb-450a-8078-e4d27b81f910)

![Image](https://github.com/user-attachments/assets/b7e02981-b3bc-4753-9d88-76bb0538534b)

![Image](https://github.com/user-attachments/assets/f2911664-823c-43f5-b6c7-fed9c7d09d55)
