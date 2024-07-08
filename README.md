* Tải elastich search
- Mở config/elasticsearch.yml để config lại:![image](https://github.com/zTrinh121/elastichsearch/assets/137372274/f48e87a6-e69f-4872-896d-7b42a309b8b8)
- Mở cmd trong bin vào tạo view tên "product":
    $cred = Get-Credential
    Invoke-RestMethod -Uri "http://localhost:9200/products" -Method Put -ContentType "application/json" -Body '{ "settings" : { "index" : { } }}' -Credential $cred
- Sau đó chạy file bin/elasticsearch.bat

- Tạo db product mysql

- Tải logstack
- Trong config của logstack tạo 1 file product_config.conf với nội dung nhưu dưới
-----------------
input {
    jdbc {
        jdbc_driver_library => "your_path/logstash-conf/mysql-connector-j-8.0.31.jar"
        jdbc_driver_class => "com.mysql.cj.jdbc.Driver"
        jdbc_connection_string => "jdbc:mysql://localhost:3306/demodb"
        jdbc_user => "your_username"
        jdbc_password => "your_password"
        statement => "SELECT * FROM demodb.product"
        use_column_value => true
        tracking_column => "created_at"
        schedule => "*/1 * * * *"
    }
}

filter {
    mutate {
	copy => { "id" => "{metadata}{_id}"}
        remove_field => ["@version"]
    }
}

output {
    elasticsearch {
        hosts => ["http://localhost:9200"]  # Use HTTP instead of HTTPS
        index => "product"
        user => "your_user_elasticsearch"
        password => "your_password_elastichsearch"
        ssl => false  # Use `ssl => false` to explicitly disable SSL
    }
}

----------------
- Mở cmd của logstash/bin chạy lệnh: logstash -f .\config\product_config.conf --config.reload.automatic
    + Với .\config\product_config.conf: là đường dẫn tới file

