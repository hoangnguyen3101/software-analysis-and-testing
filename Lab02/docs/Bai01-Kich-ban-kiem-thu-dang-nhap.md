# Bài 1 — Kịch bản kiểm thử chức năng Đăng nhập

## 1. Mô tả yêu cầu

Màn hình đăng nhập gồm:

| Thành phần | Ràng buộc |
| --- | --- |
| Tên đăng nhập | Số ký tự trong khoảng **[6 – 20]** |
| Mật khẩu | Số ký tự trong khoảng **[6 – 25]** |
| Button "Đăng nhập" | Thực hiện xác thực tài khoản |

Quy tắc khóa tài khoản: nếu đăng nhập **thất bại quá 5 lần**, hệ thống khóa tài khoản người dùng và hiển thị thông báo:

> "Tài khoản đã bị khóa do đăng nhập thất bại quá 5 lần. Bạn vui lòng đăng nhập lại sau 15 phút"

## 2. Phân tích vùng tương đương và giá trị biên cho các trường nhập liệu

### 2.1. Trường Tên đăng nhập — độ dài [6 – 20]

| Vùng | Khoảng độ dài | Tính chất |
| --- | --- | --- |
| U1 | 0 (bỏ trống) | Không hợp lệ |
| U2 | 1 – 5 | Không hợp lệ (ngắn hơn giới hạn dưới) |
| U3 | 6 – 20 | **Hợp lệ** |
| U4 | Từ 21 trở lên | Không hợp lệ (dài hơn giới hạn trên) |

Giá trị biên cần kiểm thử: **0, 1, 5, 6, 7, 19, 20, 21**

### 2.2. Trường Mật khẩu — độ dài [6 – 25]

| Vùng | Khoảng độ dài | Tính chất |
| --- | --- | --- |
| P1 | 0 (bỏ trống) | Không hợp lệ |
| P2 | 1 – 5 | Không hợp lệ (ngắn hơn giới hạn dưới) |
| P3 | 6 – 25 | **Hợp lệ** |
| P4 | Từ 26 trở lên | Không hợp lệ (dài hơn giới hạn trên) |

Giá trị biên cần kiểm thử: **0, 1, 5, 6, 7, 24, 25, 26**

### 2.3. Số lần đăng nhập thất bại liên tiếp

| Vùng | Số lần thất bại | Trạng thái tài khoản |
| --- | --- | --- |
| F1 | 0 – 4 | Bình thường, còn được phép đăng nhập |
| F2 | 5 | Bị khóa, hiển thị thông báo khóa |
| F3 | Lớn hơn 5 | Vẫn bị khóa |

Giá trị biên cần kiểm thử: **0, 1, 4, 5, 6**

### 2.4. Thời gian chờ mở khóa — 15 phút

| Vùng | Thời gian kể từ lúc bị khóa | Kết quả |
| --- | --- | --- |
| T1 | Nhỏ hơn 15 phút | Vẫn bị khóa |
| T2 | Từ 15 phút trở lên | Được mở khóa, đăng nhập lại bình thường |

Giá trị biên cần kiểm thử: **14 phút 59 giây, 15 phút 00 giây, 15 phút 01 giây**

## 3. Danh sách kịch bản kiểm thử

### 3.1. Nhóm A — Kiểm thử giao diện (UI)

| ID | Kịch bản kiểm thử | Kết quả mong đợi |
| --- | --- | --- |
| A01 | Mở màn hình đăng nhập | Hiển thị đủ 3 thành phần: ô Tên đăng nhập, ô Mật khẩu, button Đăng nhập |
| A02 | Kiểm tra nhãn, placeholder, chính tả của các trường | Đúng thiết kế, không sai chính tả |
| A03 | Kiểm tra ô Mật khẩu | Ký tự nhập vào bị che, có thể bật/tắt hiển thị mật khẩu |
| A04 | Kiểm tra thứ tự tab (Tab / Shift+Tab) | Di chuyển đúng thứ tự: Tên đăng nhập → Mật khẩu → button Đăng nhập |
| A05 | Nhấn phím Enter khi đang ở ô Mật khẩu | Kích hoạt chức năng đăng nhập như khi bấm button |
| A06 | Kiểm tra button Đăng nhập khi form còn trống | Button bị vô hiệu hóa, hoặc bấm vào thì báo lỗi bắt buộc nhập |
| A07 | Kiểm tra hiển thị thông báo lỗi | Thông báo rõ ràng, đúng vị trí, đúng ngôn ngữ |

### 3.2. Nhóm B — Kiểm thử trường Tên đăng nhập [6 – 20]

| ID | Kịch bản kiểm thử | Dữ liệu vào (độ dài) | Kết quả mong đợi |
| --- | --- | --- | --- |
| B01 | Bỏ trống tên đăng nhập | 0 | Báo lỗi "Tên đăng nhập không được để trống" |
| B02 | Nhập 1 ký tự | 1 | Báo lỗi độ dài phải từ 6 đến 20 ký tự |
| B03 | Nhập 5 ký tự (biên dưới trừ 1) | 5 | Báo lỗi độ dài phải từ 6 đến 20 ký tự |
| B04 | Nhập 6 ký tự (biên dưới) | 6 | Chấp nhận, cho phép submit |
| B05 | Nhập 7 ký tự (biên dưới cộng 1) | 7 | Chấp nhận, cho phép submit |
| B06 | Nhập 13 ký tự (giá trị giữa vùng hợp lệ) | 13 | Chấp nhận, cho phép submit |
| B07 | Nhập 19 ký tự (biên trên trừ 1) | 19 | Chấp nhận, cho phép submit |
| B08 | Nhập 20 ký tự (biên trên) | 20 | Chấp nhận, cho phép submit |
| B09 | Nhập 21 ký tự (biên trên cộng 1) | 21 | Báo lỗi độ dài, hoặc ô nhập chặn không cho gõ quá 20 ký tự |
| B10 | Nhập toàn khoảng trắng | 6 dấu cách | Báo lỗi, không tính khoảng trắng là ký tự hợp lệ |
| B11 | Nhập có khoảng trắng ở đầu/cuối | " user01 " | Hệ thống cắt khoảng trắng (trim) rồi mới kiểm tra độ dài |
| B12 | Nhập ký tự đặc biệt hoặc tiếng Việt có dấu | "nguyễn@#$" | Báo lỗi ký tự không hợp lệ theo đặc tả |
| B13 | Kiểm tra phân biệt chữ hoa/thường | "USER01" so với "user01" | Xử lý đúng theo đặc tả (thường là không phân biệt hoa thường) |
| B14 | Nhập tên đăng nhập không tồn tại trong hệ thống | "notexist99" | Báo lỗi "Tên đăng nhập hoặc mật khẩu không đúng" |

### 3.3. Nhóm C — Kiểm thử trường Mật khẩu [6 – 25]

| ID | Kịch bản kiểm thử | Dữ liệu vào (độ dài) | Kết quả mong đợi |
| --- | --- | --- | --- |
| C01 | Bỏ trống mật khẩu | 0 | Báo lỗi "Mật khẩu không được để trống" |
| C02 | Nhập 1 ký tự | 1 | Báo lỗi độ dài phải từ 6 đến 25 ký tự |
| C03 | Nhập 5 ký tự (biên dưới trừ 1) | 5 | Báo lỗi độ dài phải từ 6 đến 25 ký tự |
| C04 | Nhập 6 ký tự (biên dưới) | 6 | Chấp nhận, cho phép submit |
| C05 | Nhập 7 ký tự (biên dưới cộng 1) | 7 | Chấp nhận, cho phép submit |
| C06 | Nhập 15 ký tự (giá trị giữa vùng hợp lệ) | 15 | Chấp nhận, cho phép submit |
| C07 | Nhập 24 ký tự (biên trên trừ 1) | 24 | Chấp nhận, cho phép submit |
| C08 | Nhập 25 ký tự (biên trên) | 25 | Chấp nhận, cho phép submit |
| C09 | Nhập 26 ký tự (biên trên cộng 1) | 26 | Báo lỗi độ dài, hoặc ô nhập chặn không cho gõ quá 25 ký tự |
| C10 | Nhập mật khẩu toàn khoảng trắng | 6 dấu cách | Báo lỗi |
| C11 | Nhập mật khẩu có ký tự đặc biệt hợp lệ | "P@ss123!" | Chấp nhận, đăng nhập thành công nếu đúng mật khẩu |
| C12 | Kiểm tra phân biệt chữ hoa/thường của mật khẩu | "Abc123" so với "abc123" | Mật khẩu phải phân biệt hoa/thường |
| C13 | Nhập đúng tên đăng nhập nhưng sai mật khẩu | — | Báo lỗi chung, tăng số lần thất bại lên 1 |

### 3.4. Nhóm D — Kiểm thử chức năng đăng nhập

| ID | Kịch bản kiểm thử | Kết quả mong đợi |
| --- | --- | --- |
| D01 | Đăng nhập với tên đăng nhập và mật khẩu **đúng**, độ dài hợp lệ | Đăng nhập thành công, chuyển sang trang chủ |
| D02 | Tên đăng nhập **đúng**, mật khẩu **sai** | Thất bại, báo lỗi chung, tăng bộ đếm thất bại lên 1 |
| D03 | Tên đăng nhập **sai**, mật khẩu **đúng** | Thất bại, báo lỗi chung |
| D04 | Cả hai trường đều **sai** | Thất bại, báo lỗi chung |
| D05 | Bỏ trống cả hai trường rồi bấm Đăng nhập | Báo lỗi bắt buộc nhập ở cả hai trường |
| D06 | Chỉ nhập tên đăng nhập, bỏ trống mật khẩu | Báo lỗi ở trường Mật khẩu |
| D07 | Chỉ nhập mật khẩu, bỏ trống tên đăng nhập | Báo lỗi ở trường Tên đăng nhập |
| D08 | Đăng nhập bằng tài khoản đã bị vô hiệu hóa hoặc chưa kích hoạt | Báo lỗi tương ứng, không cho vào hệ thống |
| D09 | Bấm button Đăng nhập nhiều lần liên tiếp (double click) | Chỉ gửi 1 request, không tính thành 2 lần thất bại |
| D10 | Sau khi thất bại, sửa lại đúng thông tin rồi đăng nhập | Đăng nhập thành công, bộ đếm thất bại được **reset về 0** |
| D11 | Kiểm tra nội dung thông báo lỗi khi sai thông tin | Không tiết lộ trường nào sai, chỉ báo chung "Tên đăng nhập hoặc mật khẩu không đúng" |

### 3.5. Nhóm E — Kiểm thử quy tắc khóa tài khoản sau 5 lần thất bại

| ID | Kịch bản kiểm thử | Số lần thất bại | Kết quả mong đợi |
| --- | --- | --- | --- |
| E01 | Đăng nhập sai 1 lần | 1 | Báo sai thông tin, tài khoản vẫn hoạt động |
| E02 | Đăng nhập sai 4 lần liên tiếp (biên vùng khóa trừ 1) | 4 | Chưa khóa, vẫn cho phép đăng nhập lần tiếp theo |
| E03 | Sai 4 lần rồi lần thứ 5 nhập **đúng** | 4 | Đăng nhập thành công, bộ đếm reset về 0 |
| E04 | Đăng nhập sai 5 lần liên tiếp (**giá trị biên**) | 5 | Tài khoản **bị khóa**, hiển thị đúng thông báo: "Tài khoản đã bị khóa do đăng nhập thất bại quá 5 lần. Bạn vui lòng đăng nhập lại sau 15 phút" |
| E05 | Sau khi bị khóa, thử lần thứ 6 với mật khẩu **sai** | 6 | Vẫn hiển thị thông báo khóa, không báo lỗi sai mật khẩu |
| E06 | Sau khi bị khóa, thử lần thứ 6 với mật khẩu **đúng** | 6 | Vẫn **bị từ chối**, hiển thị thông báo khóa |
| E07 | Kiểm tra nội dung và chính tả thông báo khóa | 5 | Thông báo hiển thị **chính xác từng ký tự** như đặc tả |
| E08 | Sai xen kẽ: sai 3 lần → đúng 1 lần → sai 3 lần | — | Không bị khóa, vì bộ đếm đã reset sau lần đăng nhập đúng |
| E09 | Sai 5 lần trên tài khoản A, sau đó đăng nhập tài khoản B | — | Tài khoản B **không bị ảnh hưởng**, khóa áp dụng theo từng tài khoản |
| E10 | Sai 5 lần, đóng trình duyệt hoặc xóa cookie rồi mở lại | — | Vẫn bị khóa (trạng thái khóa lưu ở server, không phải ở client) |
| E11 | Sai 5 lần từ máy A, rồi đăng nhập từ máy B hoặc trình duyệt khác | — | Vẫn bị khóa |
| E12 | Sai 5 lần với tên đăng nhập **không tồn tại** | 5 | Xử lý theo đặc tả, không tiết lộ tài khoản có tồn tại hay không |
| E13 | Kiểm tra ghi log số lần thất bại và thời điểm khóa | — | Hệ thống ghi nhận đầy đủ |

### 3.6. Nhóm F — Kiểm thử mở khóa sau 15 phút

| ID | Kịch bản kiểm thử | Thời gian chờ | Kết quả mong đợi |
| --- | --- | --- | --- |
| F01 | Đăng nhập ngay sau khi bị khóa | 0 phút | Vẫn bị khóa |
| F02 | Đăng nhập sau 7 phút (giữa vùng còn khóa) | 7 phút | Vẫn bị khóa |
| F03 | Đăng nhập sau 14 phút 59 giây (**biên**) | 14:59 | Vẫn bị khóa |
| F04 | Đăng nhập sau đúng 15 phút với thông tin **đúng** (**biên**) | 15:00 | Mở khóa, đăng nhập **thành công** |
| F05 | Đăng nhập sau 15 phút 01 giây với thông tin **đúng** | 15:01 | Đăng nhập thành công |
| F06 | Đăng nhập sau 15 phút với thông tin **sai** | 15:00 | Được phép thử lại, bộ đếm thất bại bắt đầu lại từ 1 |
| F07 | Sau khi mở khóa, tiếp tục nhập sai 5 lần nữa | — | Bị khóa lại lần 2, cùng thông báo và cùng thời gian chờ 15 phút |
| F08 | Đổi giờ hệ thống ở máy client rồi đăng nhập lại | — | Thời gian khóa tính theo **giờ server**, không bị qua mặt |

### 3.7. Nhóm G — Kiểm thử bảo mật

| ID | Kịch bản kiểm thử | Kết quả mong đợi |
| --- | --- | --- |
| G01 | Nhập chuỗi SQL Injection vào ô đăng nhập | Bị từ chối, không đăng nhập được, không lộ lỗi hệ thống |
| G02 | Nhập chuỗi XSS dạng thẻ script | Dữ liệu bị escape, không thực thi script |
| G03 | Kiểm tra mật khẩu khi truyền đi | Truyền qua HTTPS, không gửi dạng plain-text trên URL |
| G04 | Kiểm tra mật khẩu lưu trong CSDL | Được băm (hash), không lưu dạng rõ |
| G05 | Xem mã nguồn trang hoặc DevTools | Không lộ mật khẩu, không lộ số lần thất bại còn lại |
| G06 | Gọi trực tiếp API đăng nhập, bỏ qua kiểm tra ở giao diện | Server vẫn kiểm tra độ dài 6–20 / 6–25 và quy tắc khóa 5 lần |
| G07 | Gửi hàng loạt request đăng nhập tự động (brute force) | Bị chặn bởi cơ chế khóa 5 lần, có thể kèm CAPTCHA hoặc giới hạn tần suất |
| G08 | Truy cập thẳng URL trang chủ khi chưa đăng nhập | Bị chuyển hướng về màn hình đăng nhập |
| G09 | Bấm nút Back của trình duyệt sau khi đăng xuất | Không quay lại được phiên đã đăng nhập |

### 3.8. Nhóm H — Kiểm thử phi chức năng

| ID | Kịch bản kiểm thử | Kết quả mong đợi |
| --- | --- | --- |
| H01 | Thời gian phản hồi khi bấm Đăng nhập | Phản hồi trong ngưỡng cho phép (ví dụ không quá 3 giây) |
| H02 | Nhiều người dùng đăng nhập đồng thời | Hệ thống ổn định, đếm số lần thất bại đúng cho từng tài khoản |
| H03 | Kiểm thử trên Chrome, Firefox, Edge, Safari | Giao diện và chức năng hoạt động giống nhau |
| H04 | Kiểm thử trên desktop, tablet, mobile | Giao diện responsive, thao tác bình thường |
| H05 | Mất kết nối mạng giữa chừng | Báo lỗi kết nối, **không** tính là 1 lần đăng nhập thất bại |
| H06 | Kiểm thử khả năng truy cập bằng trình đọc màn hình | Các ô nhập có label, thông báo lỗi được đọc đúng |

## 4. Tổng hợp

| Nhóm kịch bản | Số lượng | Trọng tâm |
| --- | --- | --- |
| A — Giao diện | 7 | Bố cục, hiển thị, thao tác bàn phím |
| B — Tên đăng nhập [6–20] | 14 | Vùng tương đương và giá trị biên |
| C — Mật khẩu [6–25] | 13 | Vùng tương đương và giá trị biên |
| D — Chức năng đăng nhập | 11 | Luồng đúng/sai, thông báo lỗi |
| E — Khóa tài khoản sau 5 lần | 13 | Bộ đếm thất bại, thông báo khóa |
| F — Mở khóa sau 15 phút | 8 | Giá trị biên thời gian |
| G — Bảo mật | 9 | Injection, brute force, phân quyền |
| H — Phi chức năng | 6 | Hiệu năng, tương thích, khả dụng |
| **Tổng cộng** | **81** | |
