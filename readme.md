# Tugas Praktikum PAM Pertemuan 7 - Local Data Storage
**MyProfileApp - Notes Feature**

* **Nama:** Cikal Galih Nur Arifin
* **NIM:** 123140109
* **Program Studi:** Teknik Informatika, Institut Teknologi Sumatera

---

## 🚀 Deskripsi Tugas
Branch `week-7` ini berisi pengembangan fitur Catatan (Notes) pada MyProfileApp yang mengimplementasikan arsitektur *Offline-First* sesuai dengan kriteria Modul 7.

---

## 📸 Bukti Penyelesaian Tugas (Screenshots)
Tabel berikut mendemonstrasikan pemenuhan 6 poin utama fitur aplikasi beserta bukti visualnya:

| No. | Fitur | Bukti Screenshot |
|:---:|:---|:---|
| **1** | **Daftar Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 150405" src="https://github.com/user-attachments/assets/688f5d84-aaca-466e-b974-08aa1766424e" /> |
| **2** | **Formulir Tambah Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 150843" src="https://github.com/user-attachments/assets/89bcf63d-85dd-4645-a170-f560af31df52" /> |
| **3** | **Hasil Setelah Menambah Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 151117" src="https://github.com/user-attachments/assets/838ec591-4492-4d02-9e23-01b3cb984338" /> |
| **4** | **Formulir Edit Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 151707" src="https://github.com/user-attachments/assets/ae53694f-e277-4f3c-aa0d-d5faff76d614" /> |
| **5** | **Hasil Setelah Mengedit Catatan** | <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/08ebae05-9f7f-46ea-9003-0eb39bdfeed5" /> |
| **6** | **Halaman Favorites** | <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/b38dd144-bdde-4e56-a2ac-2f4e6fc12955" /> |
| **7** | **Halaman Profile** | <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/a7074c9d-2725-4163-9c50-11814a3ce175" /> |
| **8** | **Hapus Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 154717" src="https://github.com/user-attachments/assets/f02ddde5-cf5e-4942-8698-4ee8b0891973" /> |
| **9** | **Hasil Setelah Menghapus Catatan** | <img width="1919" height="1079" alt="Screenshot 2026-04-26 154922" src="https://github.com/user-attachments/assets/9ac5f96e-3d7f-4596-91d7-c8c1b48b7340" /> |
| **10** | **Fitur Search** | <img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/599ffc96-cc4d-4f20-8f74-0002c453edb0" /> |

---

## 🗄️ Database Schema (`Note.sq`)
Skema database yang digunakan, lengkap dengan konversi tipe data otomatis (*ColumnAdapter*) untuk status favorit:
```sql
CREATE TABLE Note (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    isFavorite INTEGER AS Boolean NOT NULL DEFAULT 0,
    created_at INTEGER NOT NULL
);

## 🎥 Video Demo Aplikasi
https://github.com/user-attachments/assets/9e04cfee-f223-4508-a156-3616a1225120

