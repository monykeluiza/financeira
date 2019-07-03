@echo off
cls

REM Define o usuário e senha do banco de dados
set dbUser=root
set dbPassword=mon2812

REM Define a pasta que será feito o backup no padrão ...\<dia do mês>\<hora atual>
set backupDir=C:\Backup\MySQL\

REM Nome do arquivo que será gerado
set file=saf_%date:~0,2%%date:~3,2%%date:~6,4%.sql

REM Caminho dos executáveis do mysqldump.exe, para executar o dump, e do 7z.exe, para compactar o arquivo
set mysqldump="C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqldump.exe"

set googledrive="C:\Users\vazoli10\Google Drive\SAF\BKP\"

REM Cria a pasta de backup caso não exista
if not exist "%backupDir%" (
    mkdir "%backupDir%"
)

REM Executa o dump, aqui precisa configurar o host e o nome do banco de dados (locais com xxx)
%mysqldump% --host="localhost" --user=%dbUser% --password=%dbPassword% financeira > "%backupDir%\%file%"

copy %backupDir%\%file% %googledrive%

REM Compacta o arquivo com o dump
REM %zip% a -tgzip "%backupDir%\%file%.gz" "%backupDir%\%file%"

REM Exclui o arquivo .sql original
REM del "%backupDir%\%file%"