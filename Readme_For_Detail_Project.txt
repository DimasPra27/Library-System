Library System merupakan sistem perpustakaan untuk melakukan 
peminjaman dan pengembalian buku terdiri dari : 
1. CRUD Buku
GET /books -> mengambil semua buku
GET /books/{id} -> mengambil buku by id
POST /books -> create buku
req : 
{
    "name" : {bookName},
    "quantity" : {quantity},
    "price" : {bookPrice}
}
PUT /books/{id} -> update buku
req : 
req : 
{
    "name" : {bookName},
    "quantity" : {quantity},
    "price" : {bookPrice}
}
DELETE /books/{id} -> hard delete buku
POST /books/{id} -> soft delete buku
2. CRUD Customer
GET /customers -> mengambil semua customers
GET /customers/{id} -> mengambil customers by id
POST /customers -> create customers
req : 
{
    "customerName" : {customerName},
    "phone" : {phoneNumber},
    "email" : {email}
}
PUT /customers/{id} -> update customers
req : 
{
    "customerName" : {customerName},
    "phone" : {phoneNumber},
    "email" : {email}
}
DELETE /customers/{id} -> hard delete customers
POST /customers/{id} -> soft delete customers
3. Melakukan Peminjaman Buku
POST /libraries/order -> melakukan peminjaman buku
4. Melakukan Pengembalian Buku
req : 
{
    "returnedDate" : "{returnDate}",
    "customerId" : {customerId},
    "customerName" : {customerName},
    "detailOrderDTOS" : [
        {
            "bookId" : {booksId},
            "detailQuantity" : {detailQuantity} (banyaknya buku di pinjam),
            "price" : {bookPrice} (harga/book)
        }
    ]
}
POST /libraries/return -> melakukan pengembalian buku
req: {
    "customerId" : {customerId}
}

Detail Project :
1. Jika user meminjam buku pastikan stock tersedia 
dan buku yang di pinjam tersedia dengan stock yang ada

2. Jika user ingin mengembalikan buku, pastikan user tersebut benar meminjam buku

3. 1 User bisa pinjem 1 buku atau lebih, dan jika user mengembalikan artinya user 
 mengembalikan semua buku

4. User hanya boleh melakukan peminjaman sekali

5. Jika user terlamabat mengembalikan buku ada denda keterlamabatan
sesuai denda yang ditentukan

Detail Tech yang digunakan: 
Java Version : 17
Spring Boot Vesion : 3.1.9
Database Migration : Flyway
run flyway docker-compose up
Authentication & Authorization : JWT
Implement Logging : slf4j
Add Unit Testing : -