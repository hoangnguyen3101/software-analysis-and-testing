# Bài 3 — Bảng quyết định: điều kiện dự thi của sinh viên

## 1. Đặc tả

1. Nếu SV đi học ≥ 80% số buổi, điểm giữa kỳ ≥ 1, điểm bài tập lớn ≥ 1 thì **được dự thi**
2. Nếu SV đủ điều kiện dự thi và có điểm giữa kỳ = 10, điểm bài tập lớn = 10 thì **được miễn thi**

### Giả định (đề bài chưa nói rõ)

| Điểm chưa rõ | Cách hiểu của bài làm |
| --- | --- |
| Miễn thi có cần đủ điều kiện dự thi không? | Có. Đề ghi rõ "nếu SV **đủ điều kiện dự thi** và…" ⇒ thiếu buổi học thì dù 10 điểm vẫn **không được dự thi** |
| Thang điểm | 0 – 10; tỷ lệ đi học 0 – 100%. Ngoài miền này là dữ liệu không hợp lệ |
| Chỉ một trong hai điểm đạt 10 | Chưa đủ để miễn thi, chỉ **được dự thi** |

## 2. Conditions và Actions

**Conditions:**

| Ký hiệu | Điều kiện | Giá trị |
| --- | --- | --- |
| C1 | Đi học ≥ 80% số buổi | T / F |
| C2 | Điểm giữa kỳ ≥ 1 | T / F |
| C3 | Điểm bài tập lớn ≥ 1 | T / F |
| C4 | Điểm giữa kỳ = 10 | T / F |
| C5 | Điểm bài tập lớn = 10 | T / F |

**Actions:**

| Ký hiệu | Hành động |
| --- | --- |
| A1 | Được dự thi |
| A2 | Được miễn thi |
| A3 | Không được dự thi |

**Ràng buộc giữa các điều kiện:** C4 = T ⇒ C2 = T, và C5 = T ⇒ C3 = T (đạt 10 thì đương nhiên ≥ 1). Vì vậy trong 2⁵ = 32 tổ hợp, phần lớn là **bất khả thi**; bảng rút gọn chỉ còn 5 quy tắc.

## 3. Bảng quyết định

| Điều kiện | R1 | R2 | R3 | R4 | R5 |
| --- | :-: | :-: | :-: | :-: | :-: |
| C1 — Đi học ≥ 80% | **F** | T | T | T | T |
| C2 — Giữa kỳ ≥ 1 | – | **F** | T | T | T |
| C3 — BTL ≥ 1 | – | – | **F** | T | T |
| C4 — Giữa kỳ = 10 | – | – | – | \* | **T** |
| C5 — BTL = 10 | – | – | – | \* | **T** |
| **Hành động** | **A3** Không được dự thi | **A3** Không được dự thi | **A3** Không được dự thi | **A1** Được dự thi | **A2** Được miễn thi |

`–` = don't care (không cần xét). `\*` = không đồng thời cùng bằng T, vì nếu cả hai cùng T thì rơi vào R5.

Thứ tự ưu tiên: **R1 > R2 > R3 > R5 > R4**. Ba quy tắc loại trừ được xét trước; trong nhóm đủ điều kiện thì xét miễn thi trước, còn lại là được dự thi.

## 4. Bảng test case

| ID | Quy tắc | Input (đi học %, giữa kỳ, BTL) | Expected output |
| --- | :-: | --- | --- |
| TC01 | R1 | (79, 8, 8) | **Không được dự thi** |
| TC02 | R2 | (90, 0.9, 8) | **Không được dự thi** |
| TC03 | R3 | (90, 8, 0.5) | **Không được dự thi** |
| TC04 | R4 | (85, 7, 8) | **Được dự thi** |
| TC05 | R4 | (80, 1, 1) — biên dưới của cả 3 điều kiện | **Được dự thi** |
| TC06 | R5 | (80, 10, 10) | **Được miễn thi** |
| TC07 | R4 | (100, 10, 9.9) — chỉ giữa kỳ đạt 10 | **Được dự thi** |
| TC08 | R4 | (100, 9.9, 10) — chỉ BTL đạt 10 | **Được dự thi** |
| TC09 | R1 | (79, 10, 10) — điểm tuyệt đối nhưng thiếu buổi học | **Không được dự thi** |
| TC10 | Dữ liệu sai | (101, 5, 5) | `IllegalArgumentException` |
| TC11 | Dữ liệu sai | (90, 11, 5) | `IllegalArgumentException` |
| TC12 | Dữ liệu sai | (90, 5, −1) | `IllegalArgumentException` |

TC09 là test quan trọng nhất: nó kiểm tra đúng chỗ dễ hiểu sai của đặc tả — miễn thi phải **kèm theo** điều kiện dự thi.

Hiện thực: [ExamEligibility.java](../src/main/java/ExamEligibility.java) — kiểm chứng bằng [ExamEligibilityTest.java](../src/test/java/ExamEligibilityTest.java).
