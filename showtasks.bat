call C:\Users\asus\tasks\runcrud.bat
if "%ERRORLEVEL%" == 0 goto startPage
echo.
echo Cannot start runcrud.bat

:startPage
cd C:\Users\asus\Program Files\Google\Chrome\Application
start chrome.exe http://localhost:8181/crud/v1/task/getTasks
if "%ERRORLEVEL%" == 0 goto end
echo.
echo Cannot start Google Chrome

:fail
echo.
echo There were some errors;

:end
echo.
echo Work is finished.