# Bài 1 — Bảng quyết định: chương trình khuyến mãi của website bán hàng

## 1. Đặc tả

1. Khách mới giảm 10%
2. Khách cũ giảm 15%
3. Khách có thẻ giảm giá: 25%

### Giả định (đề bài chưa nói rõ)

| Điểm chưa rõ | Cách hiểu của bài làm |
| --- | --- |
| Khách vừa là khách mới vừa là khách cũ? | Không thể xảy ra — hai loại khách loại trừ nhau |
| Khách có thẻ **và** là khách cũ thì giảm bao nhiêu? | Không cộng dồn. Lấy mức cao nhất: **25%** (thẻ giảm giá ưu tiên cao nhất) |
| Người không thuộc nhóm nào? | Không xảy ra: mọi khách hàng đều là khách mới hoặc khách cũ |

## 2. Conditions và Actions

**Conditions (điều kiện đầu vào):**

| Ký hiệu | Điều kiện | Giá trị |
| --- | --- | --- |
| C1 | Khách mới | T / F |
| C2 | Khách cũ | T / F |
| C3 | Có thẻ giảm giá | T / F |

**Actions (kết quả đầu ra):**

| Ký hiệu | Hành động |
| --- | --- |
| A1 | Giảm 10% |
| A2 | Giảm 15% |
| A3 | Giảm 25% |

## 3. Bảng quyết định đầy đủ (2³ = 8 tổ hợp)

| # | C1 Khách mới | C2 Khách cũ | C3 Có thẻ | Khả thi? | Hành động |
| :-: | :-: | :-: | :-: | --- | --- |
| 1 | T | T | T | ✘ Không — một khách không thể vừa mới vừa cũ | — |
| 2 | T | T | F | ✘ Không | — |
| 3 | T | F | T | ✔ | A3 — giảm 25% |
| 4 | T | F | F | ✔ | A1 — giảm 10% |
| 5 | F | T | T | ✔ | A3 — giảm 25% |
| 6 | F | T | F | ✔ | A2 — giảm 15% |
| 7 | F | F | T | ✘ Không — không thuộc nhóm khách nào | — |
| 8 | F | F | F | ✘ Không | — |

⇒ Chỉ **4 tổ hợp khả thi**; 4 tổ hợp còn lại là dữ liệu không hợp lệ.

## 4. Bảng quyết định rút gọn

Tổ hợp 3 và 5 cho cùng hành động nên gộp lại, dùng dấu `–` (don't care) cho điều kiện không ảnh hưởng.

| Điều kiện | R1 | R2 | R3 |
| --- | :-: | :-: | :-: |
| C1/C2 — Loại khách | – | Khách cũ | Khách mới |
| C3 — Có thẻ giảm giá | **T** | F | F |
| **Hành động** | **Giảm 25%** | **Giảm 15%** | **Giảm 10%** |

Thứ tự ưu tiên: **R1 > R2 > R3**. Kiểm tra thẻ giảm giá trước, vì khi có thẻ thì loại khách không còn ảnh hưởng.

## 5. Bảng test case

| ID | Quy tắc | Input (loại khách, có thẻ) | Expected output |
| --- | :-: | --- | --- |
| TC01 | R3 | (Khách mới, không thẻ) | Giảm **10%** |
| TC02 | R2 | (Khách cũ, không thẻ) | Giảm **15%** |
| TC03 | R1 | (Khách mới, có thẻ) | Giảm **25%** |
| TC04 | R1 | (Khách cũ, có thẻ) | Giảm **25%** |
| TC05 | R1 | Đơn 1.000.000đ, (Khách cũ, có thẻ) | Phải trả **750.000đ** |
| TC06 | Dữ liệu sai | (null, không thẻ) | `IllegalArgumentException` |
| TC07 | Dữ liệu sai | Đơn −1đ, (Khách mới, không thẻ) | `IllegalArgumentException` |

Hiện thực: [Discount.java](../src/main/java/Discount.java) — kiểm chứng bằng [DiscountTest.java](../src/test/java/DiscountTest.java).
