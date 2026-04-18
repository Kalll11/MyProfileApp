# Tugas Praktikum Minggu 5 - Navigasi Antar Layar (Notes App)

Aplikasi **Notes App** ini dikembangkan menggunakan Jetpack Compose dengan arsitektur MVVM dan Jetpack Navigation. Aplikasi ini mendemonstrasikan perpindahan antar layar (Multi-Screen Navigation), penggunaan Bottom Navigation, dan pengiriman data antar layar (Passing Arguments).

## 🚀 Fitur Navigasi
1. **Bottom Navigation (3 Tabs):** Navigasi utama untuk berpindah antara daftar Catatan (Notes), Catatan Favorit (Favorites), dan Profil Pengguna (Profile).
2. **Passing Arguments:** Mengirimkan `noteId` dari layar daftar catatan menuju layar Detail dan layar Edit.
3. **Floating Action Button (FAB):** Berfungsi sebagai pemicu navigasi ke layar tambah catatan (hanya muncul di Tab Notes).
4. **Stack Management:** Menggunakan `popBackStack()` untuk kembali ke layar sebelumnya dengan aman tanpa menumpuk *history* layar.

---

## Screenshot setiap screen

1. **Tab Notes**

<img width="417" height="739" alt="image" src="https://github.com/user-attachments/assets/6718563f-41d0-4c68-8b3e-394e50b8b9d0" />

2. **Tab Favorites**

<img width="416" height="736" alt="image" src="https://github.com/user-attachments/assets/2250d862-2a4e-4e23-ada3-948a3f824639" />

3. **Tab Profile**

<img width="422" height="746" alt="image" src="https://github.com/user-attachments/assets/10c81f24-9f8d-4872-a53f-ed6f40bcc24c" />

4. **Add Note**

<img width="417" height="741" alt="image" src="https://github.com/user-attachments/assets/b8a764db-3e0d-40af-bc7d-d0e2cb589503" />

5. **Note Detail**

<img width="416" height="744" alt="image" src="https://github.com/user-attachments/assets/e7280bc2-686e-420b-9604-8f6fee9f3934" />

6. **Edit Note**

<img width="419" height="742" alt="image" src="https://github.com/user-attachments/assets/a11830b7-bd48-4e0d-8155-eae61e390a2d" />


## 🗺️ Navigation Flow Diagram

Berikut adalah alur navigasi dari aplikasi ini:

```mermaid
graph TD
    %% Definisi Layar
    Main[Main App / Scaffold]
    
    subgraph Bottom Navigation Tabs
        Notes[Notes List Screen]
        Fav[Favorites Screen]
        Prof[Profile Screen]
    end
    
    subgraph Detail & Form Screens
        Add[Add Note Screen]
        Detail[Note Detail Screen]
        Edit[Edit Note Screen]
        EditProf[Edit Profile Screen]
    end

    %% Alur Navigasi
    Main -->|Start Destination| Notes
    Main -.->|Tab Click| Fav
    Main -.->|Tab Click| Prof

    Notes -->|Click FAB| Add
    Notes -->|Click Note Item| Detail
    Fav -->|Click Note Item| Detail
    
    Prof -->|Click 'Edit Profil'| EditProf
    
    Detail -->|Click 'Edit'| Edit

    %% Alur Kembali (Back Stack)
    Add -.->|Simpan & popBackStack| Notes
    Detail -.->|Kembali & popBackStack| Notes
    Edit -.->|Simpan & popBackStack| Detail
    EditProf -.->|Simpan & popBackStack| Prof

    classDef screen fill:#2b2b2b,stroke:#00a8ff,stroke-width:2px,color:#fff;
    classDef tab fill:#1e1e1e,stroke:#ff9f43,stroke-width:2px,color:#fff;
    
    class Add,Detail,Edit,EditProf screen;
    class Notes,Fav,Prof tab;

**## Video Demo**

https://github.com/user-attachments/assets/0d9a9772-2300-48b8-acf0-7aa12960f681
