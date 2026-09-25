# Lab05 — Kiểm thử hộp đen: Bảng quyết định (Decision Table Testing)

Xác định **Conditions** và **Actions**, lập **bảng quyết định** cho từng đặc tả, rồi chuyển mỗi quy tắc thành ca kiểm thử (input → expected output).

## Nội dung

| Bài | Yêu cầu | Bài làm | Hiện thực | Test |
| --- | --- | --- | --- | --- |
| Bài 1 | Chương trình khuyến mãi của website bán hàng | [docs/Bai01-Bang-quyet-dinh-khuyen-mai.md](docs/Bai01-Bang-quyet-dinh-khuyen-mai.md) | [Discount.java](src/main/java/Discount.java) | 7 test |
| Bài 2 | Phí bảo hiểm ô tô theo giới tính và tuổi | [docs/Bai02-Bang-quyet-dinh-bao-hiem-o-to.md](docs/Bai02-Bang-quyet-dinh-bao-hiem-o-to.md) | [CarInsurance.java](src/main/java/CarInsurance.java) | 12 test |
| Bài 3 | Điều kiện dự thi và miễn thi của sinh viên | [docs/Bai03-Bang-quyet-dinh-dieu-kien-du-thi.md](docs/Bai03-Bang-quyet-dinh-dieu-kien-du-thi.md) | [ExamEligibility.java](src/main/java/ExamEligibility.java) | 12 test |
| Bài 4 | Xét miễn phí vận chuyển | [docs/Bai04-Bang-quyet-dinh-mien-phi-van-chuyen.md](docs/Bai04-Bang-quyet-dinh-mien-phi-van-chuyen.md) | [ShippingPolicy.java](src/main/java/ShippingPolicy.java) | 13 test |

Tổng cộng **44 test case**, tất cả đều pass.

## Tóm tắt kết quả

| Bài | Số quy tắc | Ghi chú đáng nhớ |
| --- | :-: | --- |
| Bài 1 | 3 (rút gọn từ 4 tổ hợp khả thi trong 8) | Thẻ giảm giá ưu tiên cao nhất, không cộng dồn khuyến mãi |
| Bài 2 | 6 (bảng extended-entry: 2 giới tính × 3 nhóm tuổi) | Đặc tả hở ở mốc **65 tuổi**; bài làm hiểu là "từ 65 trở lên" → $1500 |
| Bài 3 | 5 (rút gọn từ 2⁵ = 32 tổ hợp) | Miễn thi **phải kèm** điều kiện dự thi: (79%, 10, 10) vẫn trượt |
| Bài 4 | 4, ưu tiên R1 > R2 > R3 > R4 | Ngoài vùng phục vụ là quy tắc ưu tiên cao nhất; kiểm thử 4 mốc tiền |

## Cấu trúc thư mục

```
Lab05
├── pom.xml
├── README.md
├── docs
│   ├── Bai01-Bang-quyet-dinh-khuyen-mai.md
│   ├── Bai02-Bang-quyet-dinh-bao-hiem-o-to.md
│   ├── Bai03-Bang-quyet-dinh-dieu-kien-du-thi.md
│   └── Bai04-Bang-quyet-dinh-mien-phi-van-chuyen.md
└── src
    ├── main/java
    │   ├── Discount.java
    │   ├── CarInsurance.java
    │   ├── ExamEligibility.java
    │   └── ShippingPolicy.java
    └── test/java
        ├── DiscountTest.java
        ├── CarInsuranceTest.java
        ├── ExamEligibilityTest.java
        └── ShippingPolicyTest.java
```

## Chạy kiểm thử

```bash
# Chay toan bo 44 test case
mvn test

# Chay rieng test cua mot bai
mvn test "-Dtest=ShippingPolicyTest"

# Chay chuong trinh minh hoa cua tung bai
mvn compile exec:java -Dexec.mainClass=Discount
mvn compile exec:java -Dexec.mainClass=CarInsurance
mvn compile exec:java -Dexec.mainClass=ExamEligibility
mvn compile exec:java -Dexec.mainClass=ShippingPolicy
```
