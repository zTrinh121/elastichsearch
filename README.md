* Tải elastich search
- Mở config/elasticsearch.yml để config lại:![image](https://github.com/zTrinh121/elastichsearch/assets/137372274/f48e87a6-e69f-4872-896d-7b42a309b8b8)
- Mở cmd trong bin vào tạo view tên "product":
    $cred = Get-Credential
    Invoke-RestMethod -Uri "http://localhost:9200/products" -Method Put -ContentType "application/json" -Body '{ "settings" : { "index" : { } }}' -Credential $cred
- Sau đó chạy file bin/elasticsearch.bat

- Tạo db product mysql ở file demodb_product.sql

- Tải logstack
- Trong config của logstack tạo 1 file product_config.conf với nội dung như trong file  product_config.conf trong github

- Mở cmd của logstash/bin chạy lệnh: logstash -f .\config\product_config.conf --config.reload.automatic
    + Với .\config\product_config.conf: là đường dẫn tới file
- Chạy dự án và test, có thể test bằng 2 cách
	+ C1: Dùng postman và test http://localhost:9200/product/ có hoạt động không
 	+ C2: Dùng Graphql và test query findAll có hoạt động không

