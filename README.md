Loyihaning umumiy maqsadi
Ushbu ilova oliy taʼlim muassasalarida talabalar to‘lovlarini boshqarish va tahlil qilish uchun mo‘ljallangan kichik RESTful back end servisi. Unda:
•	Talabalar ma’lumotlari (ism, familya, telefon raqam, kursi, guruhi, yo’nalishi) saqlanadi,
•	Har bir talabaga tegishli to‘lov tranzaksiyalari (miqdor, sana, izoh) qayd etiladi,
•	Hisobot orqali umumiy to‘lovlar summasi va tranzaksiya soni olinadi.
•	Ilova barcha REST endpointlari uchun HTTP Basic Authentication qo‘llab quvvatlanadi.
Asosiy funksional imkoniyatlar
1.	Talabalar CRUD
o	Yaratish: POST /api/students
o	Olish (barchasi/paginatsiya bilan): GET /api/students?page=0&size=20
o	Bitta ma’lumot: GET /api/students/{id}
o	Yangilash: PUT /api/students/{id}
o	O‘chirish: DELETE /api/students/{id}
2.	Tranzaksiyalar CRUD
o	Talabaga tranzaksiya qo‘shish:
POST /api/students/{studentId}/transactions
o	Barcha tranzaksiyalar (filter va pagi¬natsiya):
GET /api/transactions?from=2025-05-01&to=2025-05-31&minAmount=100&page=0&size=20
o	Bitta tranzaksiya: GET /api/transactions/{id}
o	Yangilash: PUT /api/transactions/{id}
o	O‘chirish: DELETE /api/transactions/{id}
3.	Hisobotlar
o	Bitta talabaning barcha tranzaksiyalari:
GET /api/students/{studentId}/transactions
o	Umumiy hisobot – barcha talabalarga oid jami summa va tranzaksiya soni (vaqt filtr bilan):
GET /api/reports/summary?from=2025-05-01&to=2025-06-01
