# Bài 2 — Bảng quyết định: phí bảo hiểm ô tô

## 1. Đặc tả

1. Giới tính nữ, nhỏ hơn 65 tuổi: $500
2. Giới tính nam nhỏ hơn 25 tuổi: $3000
3. Giới tính nam từ 25–64 tuổi: $1000
4. Bất kỳ ai trên 65 tuổi: $1500

### Giả định (đề bài chưa nói rõ)

| Điểm chưa rõ | Cách hiểu của bài làm |
| --- | --- |
| Đúng **65 tuổi** thì tính thế nào? Luật 1 nói "nhỏ hơn 65", luật 3 nói "25–64", luật 4 nói "trên 65" ⇒ tuổi 65 bị hở | Hiểu luật 4 là "**từ 65 tuổi trở lên**" ⇒ 65 tuổi trả **$1500** |
| Nữ dưới 25 tuổi? | Thuộc luật 1 ("nữ nhỏ hơn 65") ⇒ **$500**. Với nữ, tuổi dưới 65 không phân biệt trẻ hay không |
| Miền tuổi hợp lệ | **18 – 120**. Ngoài miền này là dữ liệu không hợp lệ (chưa đủ tuổi lái xe) |

## 2. Conditions và Actions

**Conditions:**

| Ký hiệu | Điều kiện | Giá trị |
| --- | --- | --- |
| C1 | Giới tính | Nam / Nữ |
| C2 | Nhóm tuổi | 18–24 / 25–64 / ≥ 65 |

Đây là bảng **extended-entry**: điều kiện không chỉ nhận T/F mà nhận nhiều giá trị. Số quy tắc = 2 × 3 = **6**.

**Actions:**

| Ký hiệu | Hành động |
| --- | --- |
| A1 | Phí $500 |
| A2 | Phí $1000 |
| A3 | Phí $1500 |
| A4 | Phí $3000 |

## 3. Bảng quyết định

| Điều kiện | R1 | R2 | R3 | R4 | R5 | R6 |
| --- | :-: | :-: | :-: | :-: | :-: | :-: |
| C1 — Giới tính | Nữ | Nam | Nữ | Nam | Nữ | Nam |
| C2 — Nhóm tuổi | 18–24 | 18–24 | 25–64 | 25–64 | ≥ 65 | ≥ 65 |
| **Hành động** | **$500** | **$3000** | **$500** | **$1000** | **$1500** | **$1500** |
| Nguồn | Luật 1 | Luật 2 | Luật 1 | Luật 3 | Luật 4 | Luật 4 |

Dạng rút gọn (ưu tiên từ trên xuống):

| Thứ tự | Điều kiện | Phí |
| :-: | --- | --- |
| 1 | Tuổi ≥ 65 (mọi giới tính) | $1500 |
| 2 | Nữ, tuổi < 65 | $500 |
| 3 | Nam, tuổi < 25 | $3000 |
| 4 | Nam, 25 ≤ tuổi ≤ 64 | $1000 |

## 4. Bảng test case

Mỗi quy tắc một test, cộng thêm các **giá trị biên** 24/25 và 64/65.

| ID | Quy tắc | Input (giới tính, tuổi) | Expected output |
| --- | :-: | --- | --- |
| TC01 | R1 | (Nữ, 24) | **$500** |
| TC02 | R3 | (Nữ, 25) | **$500** |
| TC03 | R3 | (Nữ, 64) | **$500** |
| TC04 | R5 | (Nữ, 65) | **$1500** |
| TC05 | R2 | (Nam, 24) | **$3000** |
| TC06 | R4 | (Nam, 25) | **$1000** |
| TC07 | R4 | (Nam, 64) | **$1000** |
| TC08 | R6 | (Nam, 65) | **$1500** |
| TC09 | R1, R2 | (Nam, 18) và (Nữ, 18) — biên dưới miền hợp lệ | **$3000** và **$500** |
| TC10 | Dữ liệu sai | (Nam, 17) | `IllegalArgumentException` |
| TC11 | Dữ liệu sai | (Nữ, 121) | `IllegalArgumentException` |
| TC12 | Dữ liệu sai | (null, 30) | `IllegalArgumentException` |

Các mốc 24→25 và 64→65 là nơi dễ sai nhất khi lập trình (nhầm `<` với `<=`), nên phải có test ở cả hai phía của mốc.

Hiện thực: [CarInsurance.java](../src/main/java/CarInsurance.java) — kiểm chứng bằng [CarInsuranceTest.java](../src/test/java/CarInsuranceTest.java).
