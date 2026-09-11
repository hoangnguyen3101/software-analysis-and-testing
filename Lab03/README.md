# Lab03 — Kiểm thử hộp trắng: CFG, độ phức tạp chu trình và độ bao phủ

## Nội dung

| Yêu cầu | Sản phẩm |
| --- | --- |
| 1. Vẽ Control Flow Graph cho `Triangle.classify` | [docs/Bai01-CFG-va-do-bao-phu-Triangle.md — mục 1](docs/Bai01-CFG-va-do-bao-phu-Triangle.md#1-control-flow-graph-cfg) |
| 2. Tính Cyclomatic Complexity | [mục 2](docs/Bai01-CFG-va-do-bao-phu-Triangle.md#2-độ-phức-tạp-chu-trình-cyclomatic-complexity) — **CC = 5** |
| 3. Liệt kê các đường đi độc lập | [mục 3](docs/Bai01-CFG-va-do-bao-phu-Triangle.md#3-các-đường-đi-độc-lập-basis-paths) — P1 … P5 |
| 4. Test case đạt 100% Statement và Branch Coverage | [mục 4](docs/Bai01-CFG-va-do-bao-phu-Triangle.md#4-thiết-kế-test-case) — hiện thực trong [src/main/java/Triangle.java](src/main/java/Triangle.java), kiểm chứng bằng [src/test/java/TriangleTest.java](src/test/java/TriangleTest.java) |
| Tài liệu ôn tập | [docs/Lab03-Tai-lieu-on-tap.docx](docs/Lab03-Tai-lieu-on-tap.docx) — giải thích từng bước, hình minh họa, câu hỏi tự luyện có đáp án |

## Cấu trúc thư mục

```
Lab03
├── pom.xml
├── README.md
├── docs
│   ├── Bai01-CFG-va-do-bao-phu-Triangle.md
│   ├── Lab03-Tai-lieu-on-tap.docx
│   └── images/                 # CFG, cac mien, 5 duong di doc lap
│       ├── cfg.png
│       ├── cfg_regions.png
│       ├── paths.png
│       └── split_d1.png
├── tools                       # script Python sinh hinh va file docx
│   ├── figures.py
│   ├── build_docx.py
│   └── requirements.txt
└── src
    ├── main/java/Triangle.java
    └── test/java/TriangleTest.java
```

## Chạy chương trình và kiểm thử

```bash
# Chay 5 test case va sinh bao cao do bao phu JaCoCo
mvn test
# -> target/site/jacoco/index.html

# Chay chuong trinh minh hoa
mvn compile exec:java -Dexec.mainClass=Triangle
```

## Vẽ lại hình và tạo lại tài liệu ôn tập

Hình trong `docs/images` và file `docs/Lab03-Tai-lieu-on-tap.docx` được sinh từ các script trong `tools/`:

```bash
pip install -r tools/requirements.txt

# Ve lai 4 hinh -> docs/images/*.png
python tools/figures.py

# Tao lai file Word tu cac hinh tren -> docs/Lab03-Tai-lieu-on-tap.docx
python tools/build_docx.py
```
