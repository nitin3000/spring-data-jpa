set POSTGRES_HOME=C:\Users\geeta\CFA-l2\pgsql
cd %POSTGRES_HOME%
%POSTGRES_HOME%/bin/pg_ctl.exe start -D %POSTGRES_HOME%\data -l server.log