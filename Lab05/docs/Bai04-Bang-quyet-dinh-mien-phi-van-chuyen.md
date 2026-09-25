# Bài 4 — Bảng quyết định: xét miễn phí vận chuyển

## 1. Đặc tả

1. Đơn hàng **từ 500.000đ trở lên** được miễn phí vận chuyển
2. Thành viên **VIP** được miễn phí vận chuyển nếu đơn hàng **từ 200.000đ trở lên**
3. Đơn giao đến **khu vực ngoài phạm vi phục vụ** không được chấp nhận, bất kể giá trị đơn hay loại khách
4. Đơn được chấp nhận nhưng không đủ điều kiện miễn phí phải trả **30.000đ**

### Giả định

| Điểm chưa rõ | Cách hiểu của bài làm |
| --- | --- |
| "Từ … trở lên" | Bao gồm mốc: `>= 500.000` và `>= 200.000` |
| Giá trị đơn hợp lệ | `>= 0`; số âm là dữ liệu không hợp lệ |
| Khách thường dưới 500.000đ | Thu 30.000đ (luật 4) |

## 2. Conditions và Actions

**Conditions:**

| Ký hiệu | Điều kiện | Giá trị |
| --- | --- | --- |
| C1 | Ngoài phạm vi phục vụ | T / F |
| C2 | Khách VIP | T / F |
| C3 | Đơn hàng ≥ 500.000đ | T / F |
| C4 | Đơn hàng ≥ 200.000đ | T / F |

**Actions:**

| Ký hiệu | Hành động |
| --- | --- |
| A1 | Từ chối đơn hàng |
| A2 | Miễn phí vận chuyển (0đ) |
| A3 | Thu phí vận chuyển 30.000đ |

**Ràng buộc:** C3 = T ⇒ C4 = T (đơn ≥ 500.000 thì đương nhiên ≥ 200.000). Tổ hợp C3 = T và C4 = F là **bất khả thi**.

## 3. Bảng quyết định và thứ tự ưu tiên

| Điều kiện | R1 | R2 | R3 | R4 |
| --- | :-: | :-: | :-: | :-: |
| C1 — Ngoài phạm vi phục vụ | **T** | F | F | F |
| C2 — Khách VIP | – | – | **T** | \* |
| C3 — Đơn ≥ 500.000 | – | **T** | F | F |
| C4 — Đơn ≥ 200.000 | – | – | **T** | \* |
| **Hành động** | **A1** Từ chối | **A2** Miễn phí | **A2** Miễn phí | **A3** Thu 30.000đ |

`\*` ở R4 nghĩa là: khách thường (C2 = F) với đơn bất kỳ < 500.000, **hoặc** khách VIP với đơn < 200.000.

**Thứ tự ưu tiên: R1 > R2 > R3 > R4.**

1. **R1 ưu tiên cao nhất** — ngoài vùng phục vụ thì từ chối ngay, không cần xét giá trị đơn hay loại khách.
2. **R2** — ngưỡng 500.000đ áp dụng cho mọi khách, kể cả khách thường.
3. **R3** — ngưỡng ưu đãi 200.000đ chỉ dành cho VIP.
4. **R4** — mặc định (default rule) cho các trường hợp còn lại.

## 4. Bảng test case

Ngoài mỗi quy tắc một test, bắt buộc kiểm thử các mốc **199.999 / 200.000 / 499.999 / 500.000** cho cả khách VIP lẫn khách thường.

| ID | Quy tắc | Input (giá trị đơn, VIP?, ngoài vùng?) | Expected output |
| --- | :-: | --- | --- |
| TC01 | R1 | (1.000.000, VIP, ngoài vùng) | **Từ chối đơn hàng** |
| TC02 | R1 | (50.000, thường, ngoài vùng) | **Từ chối đơn hàng** |
| TC03 | R2 | (500.000, thường, trong vùng) | **Miễn phí** |
| TC04 | R4 | (499.999, thường, trong vùng) | **Thu 30.000đ** |
| TC05 | R2 | (500.000, VIP, trong vùng) | **Miễn phí** |
| TC06 | R3 | (200.000, VIP, trong vùng) | **Miễn phí** |
| TC07 | R4 | (199.999, VIP, trong vùng) | **Thu 30.000đ** |
| TC08 | R3 | (499.999, VIP, trong vùng) | **Miễn phí** |
| TC09 | R4 | (200.000, thường, trong vùng) | **Thu 30.000đ** |
| TC10 | R4 | (199.999, thường, trong vùng) | **Thu 30.000đ** |
| TC11 | R4 | (0, thường, trong vùng) | **Thu 30.000đ** |
| TC12 | R1–R4 | Kiểm tra số tiền: 0đ / 30.000đ / đơn bị từ chối | **0**, **30.000**, **−1** (mã báo từ chối) |
| TC13 | Dữ liệu sai | (−1, thường, trong vùng) | `IllegalArgumentException` |

Phân tích 4 mốc theo loại khách:

| Giá trị đơn | Khách thường | Khách VIP |
| --- | --- | --- |
| 199.999 | Thu 30.000đ (TC10) | Thu 30.000đ (TC07) |
| 200.000 | Thu 30.000đ (TC09) | **Miễn phí** (TC06) |
| 499.999 | Thu 30.000đ (TC04) | **Miễn phí** (TC08) |
| 500.000 | **Miễn phí** (TC03) | **Miễn phí** (TC05) |

Hiện thực: [ShippingPolicy.java](../src/main/java/ShippingPolicy.java) — kiểm chứng bằng [ShippingPolicyTest.java](../src/test/java/ShippingPolicyTest.java).
