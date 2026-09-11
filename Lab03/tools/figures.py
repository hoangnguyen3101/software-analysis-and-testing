"""Ve cac hinh minh hoa cho Lab03 (Triangle.classify).

Sinh 4 anh PNG vao Lab03/docs/images:
    cfg.png          - Control Flow Graph muc quyet dinh
    cfg_regions.png  - 4 mien kin + mien ngoai (dem Cyclomatic Complexity)
    paths.png        - 5 duong di doc lap P1 - P5
    split_d1.png     - tach nut D1 thanh cac dieu kien don (short-circuit)

Chay (tu thu muc Lab03):
    pip install -r tools/requirements.txt
    python tools/figures.py
"""

import math
import os

import matplotlib

matplotlib.use("Agg")
import matplotlib.pyplot as plt
from matplotlib.patches import Circle, FancyArrowPatch, Polygon

OUT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "docs", "images")

plt.rcParams["font.family"] = "DejaVu Sans"

# Ban kinh nut va toa do cac nut tren CFG (so hieu nut trung voi chu thich trong Triangle.java)
R = 0.32
POS = {
    1: (0.0, 6.0),
    2: (-1.4, 4.6),
    3: (1.4, 4.6),
    4: (0.0, 3.2),
    5: (2.8, 3.2),
    6: (1.4, 1.8),
    7: (4.2, 1.8),
    8: (2.8, 0.4),
    9: (5.6, 0.4),
    10: (2.1, -1.6),
}
DECISION = {1, 3, 5, 7}
EDGES = [
    (1, 2, "T"), (1, 3, "F"),
    (3, 4, "T"), (3, 5, "F"),
    (5, 6, "T"), (5, 7, "F"),
    (7, 8, "T"), (7, 9, "F"),
    (2, 10, ""), (4, 10, ""), (6, 10, ""), (8, 10, ""), (9, 10, ""),
]
LABEL = {
    1: "D1: a<=0 || b<=0 || c<=0",
    2: '"Invalid"',
    3: "D2: a+b<=c || a+c<=b || b+c<=a",
    4: '"Not a triangle"',
    5: "D3: a==b && b==c",
    6: '"Equilateral"',
    7: "D4: a==b || b==c || a==c",
    8: '"Isosceles"',
    9: '"Scalene"',
}
PATHS = {
    "P1": [1, 2, 10],
    "P2": [1, 3, 4, 10],
    "P3": [1, 3, 5, 6, 10],
    "P4": [1, 3, 5, 7, 8, 10],
    "P5": [1, 3, 5, 7, 9, 10],
}
RESULT = {"P1": "Invalid", "P2": "Not a triangle", "P3": "Equilateral", "P4": "Isosceles", "P5": "Scalene"}

C_DEC = "#FFE0B2"   # nut quyet dinh
C_STM = "#BBDEFB"   # nut lenh
C_EXIT = "#E0E0E0"  # nut ket thuc
C_EDGE = "#37474F"
C_HI = "#D32F2F"    # to dam duong di


def node(ax, n, pos, hi=False, dim=False, r=R, text=None, fc=None, fs=13):
    x, y = pos[n] if isinstance(pos, dict) else pos
    face = fc or (C_DEC if n in DECISION else (C_EXIT if n == 10 else C_STM))
    edge = C_HI if hi else "#263238"
    alpha = 0.35 if dim else 1.0
    ax.add_patch(Circle((x, y), r, fc=face, ec=edge, lw=2.6 if hi else 1.4, alpha=alpha, zorder=3))
    if n == 10 and text is None:
        ax.add_patch(Circle((x, y), r * 0.78, fc="none", ec=edge, lw=1.1, alpha=alpha, zorder=4))
    ax.text(x, y, str(n) if text is None else text, ha="center", va="center",
            fontsize=fs, fontweight="bold", alpha=alpha, zorder=5)


def arrow(ax, p, q, label="", hi=False, dim=False, r=R, label_side=None):
    (x1, y1), (x2, y2) = p, q
    d = math.hypot(x2 - x1, y2 - y1)
    ux, uy = (x2 - x1) / d, (y2 - y1) / d
    # rut ngan mui ten de khong de len hinh tron
    ax.add_patch(FancyArrowPatch((x1 + ux * r, y1 + uy * r), (x2 - ux * r, y2 - uy * r),
                                 arrowstyle="-|>", mutation_scale=16, lw=2.8 if hi else 1.4,
                                 color=C_HI if hi else C_EDGE, alpha=0.3 if dim else 1.0, zorder=2))
    if label:
        mx, my = (x1 + x2) / 2, (y1 + y2) / 2
        nx, ny = -uy, ux
        if label_side == "right" or (label_side is None and label == "F"):
            nx, ny = -nx, -ny
        # dat nhan ve phia ben ngoai cua canh
        if ux < 0:
            nx, ny = -nx, -ny
        ax.text(mx + nx * 0.22, my + ny * 0.22, label, ha="center", va="center", fontsize=11,
                fontweight="bold", color="#1B5E20" if label == "T" else "#B71C1C",
                alpha=0.35 if dim else 1.0, zorder=6)


def base_cfg(ax, hi_edges=(), hi_nodes=(), show_labels=True, dim_others=False, fs=13):
    for (u, v, lab) in EDGES:
        h = (u, v) in hi_edges
        arrow(ax, POS[u], POS[v], lab, hi=h, dim=dim_others and not h)
    for n in POS:
        h = n in hi_nodes
        node(ax, n, POS, hi=h, dim=dim_others and not h, fs=fs)
    if show_labels:
        for n, (x, y) in POS.items():
            if n in DECISION:
                ax.text(x + 0.42, y + 0.25, LABEL[n], fontsize=8.5, color="#E65100", ha="left", va="bottom",
                        family="DejaVu Sans Mono")
            elif n in LABEL:
                ax.text(x + 0.42, y, LABEL[n], fontsize=8.5, color="#0D47A1", ha="left", va="center",
                        family="DejaVu Sans Mono")
    ax.set_aspect("equal")
    ax.axis("off")


def save(fig, name, dpi=200):
    fig.savefig(os.path.join(OUT, name), dpi=dpi, bbox_inches="tight")
    plt.close(fig)


def fig_cfg():
    fig, ax = plt.subplots(figsize=(9.5, 7.6))
    base_cfg(ax)
    ax.set_xlim(-2.4, 8.6)
    ax.set_ylim(-2.3, 6.9)
    # chu thich
    lx, ly = -2.1, 1.3
    for i, (color, text) in enumerate([(C_DEC, "Nút quyết định (if)"), (C_STM, "Nút lệnh (return)"),
                                       (C_EXIT, "Nút kết thúc")]):
        ax.add_patch(Circle((lx, ly - 0.5 * i), 0.18, fc=color, ec="#263238"))
        ax.text(lx + 0.3, ly - 0.5 * i, text, va="center", fontsize=10)
    ax.text(lx - 0.18, ly - 1.55, "T = True,  F = False", va="center", fontsize=10)
    save(fig, "cfg.png")


def fig_regions():
    fig, ax = plt.subplots(figsize=(9.5, 7.6))
    shades = ["#C8E6C9", "#FFF9C4", "#F8BBD0", "#D1C4E9"]
    polys = [
        [POS[1], POS[3], POS[4], POS[10], POS[2]],
        [POS[3], POS[5], POS[6], POS[10], POS[4]],
        [POS[5], POS[7], POS[8], POS[10], POS[6]],
        [POS[7], POS[9], POS[10], POS[8]],
    ]
    centers = [(0.0, 4.1), (1.4, 2.7), (2.8, 1.3), (4.1, 0.3)]
    for i, (pts, c) in enumerate(zip(polys, shades)):
        ax.add_patch(Polygon(pts, closed=True, fc=c, ec="none", alpha=0.85, zorder=0))
        ax.text(centers[i][0], centers[i][1], f"R{i + 1}", ha="center", va="center", fontsize=15,
                fontweight="bold", color="#33691E", zorder=1)
    base_cfg(ax, show_labels=False)
    ax.text(5.8, 4.8, "R5 = miền bên ngoài\n(không bị bao)", fontsize=12, fontweight="bold", color="#33691E")
    ax.text(5.8, 3.6, "Tổng: 4 miền kín + 1 miền ngoài\n=> V(G) = 5", fontsize=12, color="#263238")
    ax.set_xlim(-2.4, 9.0)
    ax.set_ylim(-2.3, 6.7)
    save(fig, "cfg_regions.png")


def fig_paths():
    fig, axes = plt.subplots(2, 3, figsize=(10.5, 7.6))
    axes = axes.ravel()
    for ax, (name, seq) in zip(axes, PATHS.items()):
        base_cfg(ax, hi_edges=set(zip(seq, seq[1:])), hi_nodes=set(seq), show_labels=False,
                 dim_others=True, fs=11)
        ax.set_xlim(-2.0, 6.3)
        ax.set_ylim(-2.2, 6.6)
        ax.set_title(f"{name}: " + " → ".join(map(str, seq)) + f"\n=> {RESULT[name]}", fontsize=12,
                     fontweight="bold", color="#B71C1C")
    # o cuoi: tong hop canh moi cua tung duong
    ax = axes[5]
    ax.axis("off")
    txt = ("Mỗi đường đi độc lập thêm\nít nhất 1 cạnh MỚI:\n\n"
           "P1: 1→2, 2→10\n"
           "P2: 1→3, 3→4, 4→10\n"
           "P3: 3→5, 5→6, 6→10\n"
           "P4: 5→7, 7→8, 8→10\n"
           "P5: 7→9, 9→10\n\n"
           "2 + 3 + 3 + 3 + 2 = 13 cạnh\n= toàn bộ cạnh của CFG")
    ax.text(0.0, 0.5, txt, fontsize=11.5, va="center",
            bbox=dict(boxstyle="round,pad=0.8", fc="#FFFDE7", ec="#F9A825"))
    fig.tight_layout()
    save(fig, "paths.png", dpi=180)


def fig_split():
    fig, axes = plt.subplots(1, 2, figsize=(10, 4.7), gridspec_kw={"width_ratios": [1, 1.5]})
    mono = dict(fontsize=13, family="DejaVu Sans Mono", color="#E65100")

    # trai: D1 la 1 nut quyet dinh
    ax = axes[0]
    p = {1: (1.5, 3.0), 2: (0.3, 1.2), 3: (2.7, 1.2)}
    arrow(ax, p[1], p[2], "T")
    arrow(ax, p[1], p[3], "F")
    node(ax, 1, p[1], fc=C_DEC, text="1", fs=16)
    node(ax, 2, p[2], fc=C_STM, text="2", fs=16)
    node(ax, 3, p[3], fc=C_DEC, text="3", fs=16)
    ax.text(1.5, 3.55, "a<=0 || b<=0 || c<=0", ha="center", **mono)
    ax.text(1.5, 0.15, "Mức QUYẾT ĐỊNH\n1 nút, 2 nhánh", ha="center", fontsize=14, fontweight="bold")
    ax.set_xlim(-0.5, 3.5)
    ax.set_ylim(-0.4, 4.0)
    ax.set_aspect("equal")
    ax.axis("off")

    # phai: tach D1 thanh 3 nut dieu kien don
    ax = axes[1]
    q = {"1a": (1.0, 3.2), "1b": (2.6, 2.2), "1c": (4.2, 1.2), "2": (0.4, 0.2), "3": (5.6, 0.2)}
    for cond, nxt in (("1a", "1b"), ("1b", "1c"), ("1c", "3")):
        arrow(ax, q[cond], q["2"], "T")
        arrow(ax, q[cond], q[nxt], "F", label_side="right")
    for k in ("1a", "1b", "1c"):
        node(ax, 1, q[k], fc=C_DEC, text=k, fs=15)
    node(ax, 2, q["2"], fc=C_STM, text="2", fs=16)
    node(ax, 3, q["3"], fc=C_DEC, text="3", fs=16)
    ax.text(0.55, 3.45, "a <= 0", ha="right", **mono)
    ax.text(3.0, 2.45, "b <= 0", ha="left", **mono)
    ax.text(4.6, 1.45, "c <= 0", ha="left", **mono)
    ax.text(3.0, -0.95, "Mức ĐIỀU KIỆN ĐƠN (short-circuit)\n3 nút, 6 nhánh", ha="center", fontsize=14,
            fontweight="bold")
    ax.set_xlim(-0.6, 6.4)
    ax.set_ylim(-1.5, 3.9)
    ax.set_aspect("equal")
    ax.axis("off")
    fig.tight_layout()
    save(fig, "split_d1.png")


if __name__ == "__main__":
    os.makedirs(OUT, exist_ok=True)
    fig_cfg()
    fig_regions()
    fig_paths()
    fig_split()
    print("Da ve xong:", ", ".join(sorted(f for f in os.listdir(OUT) if f.endswith(".png"))))
