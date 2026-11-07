# Sistem Otomotif

Program ini dibuat untuk tugas PBO dengan tema otomotif karena angka terakhir NIM adalah 8. Program bisa dipakai untuk mencatat mobil dan motor serta mengecek jadwal servisnya.

## Identitas

- Nama: Idham Khalid Munawar 
- NIM: H1D025018
- Tema: Otomotif

## Data Contoh

Saat program dibuka, tiga kendaraan berikut langsung tersedia:

| Kendaraan | Plat | Jarak | Status |
| --- | --- | ---: | --- |
| Toyota Avanza | B 1234 CD | 12.500 km | Perlu servis |
| Honda Vario | D 5678 EF | 3.200 km | Belum perlu servis |
| Yamaha NMAX | F 9012 GH | 11.000 km | Perlu servis |

Data hanya tersimpan selama program berjalan. Setelah program ditutup, perubahan akan hilang dan data contoh akan dimuat lagi.

## Menjalankan Program

Buka terminal di folder utama proyek, lalu jalankan:

```powershell
javac -d out src\otomotif\*.java
java -cp out otomotif.Main
```

## Menu

- `1`: tambah mobil atau motor
- `2`: lihat semua kendaraan
- `3`: lihat kendaraan yang perlu servis
- `4`: servis kendaraan yang perlu servis
- `5`: ubah odometer
- `6`: lihat perkiraan biaya asuransi dan pajak
- `0`: keluar

Saat memilih jenis atau kendaraan, masukkan `0` untuk kembali. Saat mengisi data, ketik `/b` untuk membatalkan dan kembali ke menu utama. Jika salah memasukkan angka, program akan meminta input lagi.

## Bagian PBO

- `Vehicle` adalah class induk. `Car` dan `Motorcycle` adalah turunannya.
- `Vehicle` menyimpan data umum kendaraan dan aturan servis.
- `Car` menghitung asuransi dan pajak. `Motorcycle` menghitung asuransi.
- `Serviceable`, `Insurable`, dan `Taxable` adalah interface yang dipakai sesuai layanan kendaraan.
- Constructor memiliki beberapa bentuk. Data dijaga dengan atribut `private`, getter, dan setter.
- Method `performService()` mengubah status servis kendaraan.

## Screenshot
Di sini, ada folder screenshots yang di mana saya sudah ss sesuai alur berurutan pada menu, petama saya menambahkan kendaraan, lalu saya melihat apalah kendaraan saya sudah terdaftar lalu saya melihat kendaraan mana yang erlu di servis, ternyata kendaraan yang saya input tadi tidak perlu diservis dikarenakan odometer yang saya input tadi hanya 1000, selanjutnya saya menservis kendaraan yang sudah ada pada program dan status kendaraan tersebut sudah tidak perlu di servis kembali, lalu saya memperbarui ordometer pada kendaraan yang say tambahkan diawal menjadi lebih banyak dari sebelumnya dan status kendaraan tersebut pun berubah menjadi perlu diservis, karena kendaraan harus diservis selanjutnya saya melihat estimasi biaya yang harus dibayarkan ketika menservis kendaraan tersebut, program selesai.  
![Hasil program](screenshots)