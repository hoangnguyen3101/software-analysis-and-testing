# Bài 2 — Kiểm thử giá vé xe điện theo giờ khởi hành

## 1. Mô tả yêu cầu

| Loại vé | Khung giờ áp dụng |
| --- | --- |
| **Vé thường** (giờ cao điểm) | Chuyến **trước 9:30 sáng**, hoặc **sau 4:00 chiều đến 7:30 tối** |
| **Vé tiết kiệm** (giá thấp hơn) | Chuyến **từ 9:30 sáng đến 4:00 chiều**, hoặc **sau 7:30 tối đến 12:00 đêm** |

Quy ước dùng trong bài:

- Thời gian biểu diễn theo định dạng 24 giờ `HH:mm`, đơn vị nhỏ nhất là **1 phút**.
- 4:00 chiều = `16:00`, 7:30 tối = `19:30`, 12:00 đêm = hết ngày, tức `23:59`.
- "Từ 9:30" và "đến 4:00 chiều" là **bao gồm** hai mốc đó, nên `09:30` và `16:00` thuộc vé tiết kiệm.
- "Sau 4:00 chiều" và "sau 7:30 tối" là **không bao gồm** mốc đó, nên `16:00` chưa phải giờ cao điểm buổi chiều, còn `19:30` vẫn là giờ cao điểm.
- Miền thời gian hợp lệ là `00:00` – `23:59`. Ngoài miền này (ví dụ `24:00`, giờ âm, phút bằng 60) là dữ liệu không hợp lệ.

## 2. Các vùng tương đương (Equivalence Partitioning)

| Vùng | Khoảng thời gian | Loại vé / Kết quả | Tính chất | Giá trị đại diện |
| --- | --- | --- | --- | --- |
| **V1** | `00:00` – `09:29` | Vé thường | Hợp lệ | `07:00` |
| **V2** | `09:30` – `16:00` | Vé tiết kiệm | Hợp lệ | `12:00` |
| **V3** | `16:01` – `19:30` | Vé thường | Hợp lệ | `17:45` |
| **V4** | `19:31` – `23:59` | Vé tiết kiệm | Hợp lệ | `21:00` |
| **V5** | Giờ nhỏ hơn 0 hoặc lớn hơn 23 | Báo lỗi | Không hợp lệ | `24:00` |
| **V6** | Phút nhỏ hơn 0 hoặc lớn hơn 59 | Báo lỗi | Không hợp lệ | `10:60` |
| **V7** | Chuỗi rỗng / sai định dạng | Báo lỗi | Không hợp lệ | `"9h30"` |

## 3. Bảng vùng mua vé thường và vùng mua vé tiết kiệm

| Loại vé | Vùng tương đương | Khoảng thời gian | Giá trị biên của vùng |
| --- | --- | --- | --- |
| **Vé thường** | V1 | `00:00` – `09:29` | `00:00` (biên dưới), `09:29` (biên trên) |
| **Vé thường** | V3 | `16:01` – `19:30` | `16:01` (biên dưới), `19:30` (biên trên) |
| **Vé tiết kiệm** | V2 | `09:30` – `16:00` | `09:30` (biên dưới), `16:00` (biên trên) |
| **Vé tiết kiệm** | V4 | `19:31` – `23:59` | `19:31` (biên dưới), `23:59` (biên trên) |
| Không hợp lệ | V5, V6, V7 | Ngoài `00:00` – `23:59` | `-00:01`, `24:00`, `10:60` |

## 4. Bảng giá trị biên (Boundary Value Analysis)

Tại mỗi mốc chuyển vùng, lấy 3 giá trị: **ngay trước biên – tại biên – ngay sau biên**.

| Mốc biên | Giá trị | Vùng | Kết quả mong đợi |
| --- | --- | --- | --- |
| Đầu ngày | `-00:01` (giờ = −1) | Ngoài miền | Báo lỗi dữ liệu không hợp lệ |
| Đầu ngày | `00:00` | V1 | **Vé thường** |
| Đầu ngày | `00:01` | V1 | **Vé thường** |
| Mốc 9:30 sáng | `09:29` | V1 | **Vé thường** |
| Mốc 9:30 sáng | `09:30` | V2 | **Vé tiết kiệm** |
| Mốc 9:30 sáng | `09:31` | V2 | **Vé tiết kiệm** |
| Mốc 4:00 chiều | `15:59` | V2 | **Vé tiết kiệm** |
| Mốc 4:00 chiều | `16:00` | V2 | **Vé tiết kiệm** |
| Mốc 4:00 chiều | `16:01` | V3 | **Vé thường** |
| Mốc 7:30 tối | `19:29` | V3 | **Vé thường** |
| Mốc 7:30 tối | `19:30` | V3 | **Vé thường** |
| Mốc 7:30 tối | `19:31` | V4 | **Vé tiết kiệm** |
| Cuối ngày | `23:59` | V4 | **Vé tiết kiệm** |
| Cuối ngày | `24:00` | Ngoài miền | Báo lỗi dữ liệu không hợp lệ |

Trục thời gian trong ngày:

```
00:00 ────────── 09:29 | 09:30 ────────── 16:00 | 16:01 ────────── 19:30 | 19:31 ────────── 23:59
      Vé thường (V1)   |   Vé tiết kiệm (V2)    |     Vé thường (V3)    |   Vé tiết kiệm (V4)
```

## 5. Bảng test case

| ID | Mục tiêu kiểm thử | Kỹ thuật | Dữ liệu vào | Kết quả mong đợi |
| --- | --- | --- | --- | --- |
| TC01 | Biên dưới của miền hợp lệ, đầu ngày | BVA | `00:00` | Vé thường |
| TC02 | Ngay trước mốc 9:30 sáng | BVA | `09:29` | Vé thường |
| TC03 | Đúng mốc 9:30 sáng | BVA | `09:30` | Vé tiết kiệm |
| TC04 | Ngay sau mốc 9:30 sáng | BVA | `09:31` | Vé tiết kiệm |
| TC05 | Ngay trước mốc 4:00 chiều | BVA | `15:59` | Vé tiết kiệm |
| TC06 | Đúng mốc 4:00 chiều | BVA | `16:00` | Vé tiết kiệm |
| TC07 | Ngay sau mốc 4:00 chiều | BVA | `16:01` | Vé thường |
| TC08 | Ngay trước mốc 7:30 tối | BVA | `19:29` | Vé thường |
| TC09 | Đúng mốc 7:30 tối | BVA | `19:30` | Vé thường |
| TC10 | Ngay sau mốc 7:30 tối | BVA | `19:31` | Vé tiết kiệm |
| TC11 | Biên trên của miền hợp lệ, cuối ngày | BVA | `23:59` | Vé tiết kiệm |
| TC12 | Đại diện vùng V1 — sáng sớm | EP | `07:00` | Vé thường |
| TC13 | Đại diện vùng V2 — giữa trưa | EP | `12:00` | Vé tiết kiệm |
| TC14 | Đại diện vùng V3 — chiều tối cao điểm | EP | `17:45` | Vé thường |
| TC15 | Đại diện vùng V4 — buổi tối muộn | EP | `21:00` | Vé tiết kiệm |
| TC16 | Giờ vượt biên trên | EP / BVA | `24:00` | Báo lỗi `IllegalArgumentException` |
| TC17 | Giờ âm | EP / BVA | giờ = `-1`, phút = `0` | Báo lỗi `IllegalArgumentException` |
| TC18 | Phút vượt biên trên | EP / BVA | giờ = `10`, phút = `60` | Báo lỗi `IllegalArgumentException` |
| TC19 | Phút âm | EP / BVA | giờ = `10`, phút = `-1` | Báo lỗi `IllegalArgumentException` |
| TC20 | Chuỗi sai định dạng | EP | `"9h30"` | Báo lỗi `IllegalArgumentException` |
| TC21 | Chuỗi rỗng | EP | `""` | Báo lỗi `IllegalArgumentException` |
| TC22 | Giá trị null | EP | `null` | Báo lỗi `IllegalArgumentException` |

## 6. Hiện thực và kiểm chứng

- Mã nguồn xử lý: [TrainTicket.java](../src/main/java/TrainTicket.java)
- Bộ test case tự động: [TrainTicketTest.java](../src/test/java/TrainTicketTest.java) — mỗi test case `TC01` – `TC22` ở bảng trên tương ứng một phương thức test.

Chạy kiểm thử:

```bash
mvn test
```

## 7. Ghi chú về điểm chưa rõ trong đề bài

Đề bài phát biểu bằng ngôn ngữ tự nhiên nên có 2 chỗ dễ gây nhập nhằng, bài làm này chọn cách hiểu như sau và ghi rõ để kiểm thử nhất quán:

1. **Mốc `16:00`**: câu "vé tiết kiệm có hiệu lực cho các chuyến từ 9:30 sáng đến 4:00 chiều" và câu "vé thường áp dụng từ **sau** 4:00 chiều" đều dẫn tới việc `16:00` thuộc **vé tiết kiệm**.
2. **Mốc `12:00` đêm**: hiểu là thời điểm kết thúc ngày. Chuyến cuối cùng còn được mua vé tiết kiệm là `23:59`; `24:00` được coi là dữ liệu không hợp lệ (thuộc về ngày hôm sau, khi đó `00:00` lại rơi vào vùng vé thường).
