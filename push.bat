@echo off
set /p msg=Enter description of changes: 
echo.
echo Adding all files
git add --all
echo.
echo Committing with message: "%msg%"
git commit -m "%msg%"
echo.
echo Pushing to repository
git push
echo.
echo Synced
pause