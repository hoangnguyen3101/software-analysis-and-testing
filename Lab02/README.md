# Lab02 — Kiểm thử hộp đen: vùng tương đương và giá trị biên

## Nội dung

| Bài | Yêu cầu | Sản phẩm |
| --- | --- | --- |
| Bài 1 | Liệt kê tất cả kịch bản kiểm thử cho trang Đăng nhập | [docs/Bai01-Kich-ban-kiem-thu-dang-nhap.md](docs/Bai01-Kich-ban-kiem-thu-dang-nhap.md) |
| Bài 2 | Vùng tương đương, giá trị biên và test case cho giá vé xe điện | [docs/Bai02-Vung-tuong-duong-va-gia-tri-bien.md](docs/Bai02-Vung-tuong-duong-va-gia-tri-bien.md) — hiện thực trong [src/main/java/TrainTicket.java](src/main/java/TrainTicket.java), kiểm chứng bằng [src/test/java/TrainTicketTest.java](src/test/java/TrainTicketTest.java) |

## Cấu trúc thư mục

```
Lab02
├── pom.xml
├── README.md
├── docs
│   ├── Bai01-Kich-ban-kiem-thu-dang-nhap.md
│   └── Bai02-Vung-tuong-duong-va-gia-tri-bien.md
└── src
    ├── main/java/TrainTicket.java
    └── test/java/TrainTicketTest.java
```

## Chạy chương trình và kiểm thử

```bash
# Chay 22 test case cua Bai 2
mvn test

# Chay chuong trinh minh hoa
mvn compile exec:java -Dexec.mainClass=TrainTicket
```
