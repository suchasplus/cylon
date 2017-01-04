<!DOCTYPE>
<html>
    <body>
    welcome ${name}  to using freemarker!
    <form action="/basic/upload" method="post" enctype="multipart/form-data" name="upload_file">
        <input type="file" name="file"/>
        <input type="submit" name="submit"/>
    </form>
    </body>
</html>