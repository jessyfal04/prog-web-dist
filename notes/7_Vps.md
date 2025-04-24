
We create a DNS record for prog-web-dist.jessyfal04.dev
We connect to the server via ssh

We create a conf for apache2
```
#/etc/apache2/sites-available/prog-web-dist.jessyfal04.dev.conf
<VirtualHost *:80>
    ServerName prog-web-dist.jessyfal04.dev
    DocumentRoot /home/yepssy/code/prog-web-dist

    ErrorLog ${APACHE_LOG_DIR}/prog-web-dist.jessyfal04.dev_error.log
    CustomLog ${APACHE_LOG_DIR}/prog-web-dist.jessyfal04.dev_access.log combined

    # Redirect all HTTP requests to HTTPS
    RewriteEngine on
    RewriteCond %{SERVER_NAME} =prog-web-dist.jessyfal04.dev
    RewriteRule ^ https://%{SERVER_NAME}%{REQUEST_URI} [END,NE,R=permanent]
</VirtualHost>

<VirtualHost *:443>
    ServerName prog-web-dist.jessyfal04.dev

    DocumentRoot /home/yepssy/code/prog-web-dist

    ProxyPass "/" "http://10.107.52.75/"
    ProxyPassReverse "/" "http://10.107.52.75/"
    
    ErrorLog ${APACHE_LOG_DIR}/prog-web-dist.jessyfal04.dev_error.log
    CustomLog ${APACHE_LOG_DIR}/prog-web-dist.jessyfal04.dev_access.log combined

Include /etc/letsencrypt/options-ssl-apache.conf
SSLCertificateFile /etc/letsencrypt/live/prog-web-dist.jessyfal04.dev/fullchain.pem
SSLCertificateKeyFile /etc/letsencrypt/live/prog-web-dist.jessyfal04.dev/privkey.pem

</VirtualHost>
```

And we generate a ssl certificate with certbot (Let's Encrypt)
[image]