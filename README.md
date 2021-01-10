"demo" represents the bank server (which stores all user and account details)
"demo_client" represents atm service (which client interacts with)

Example of requests:
1) http://localhost:8081/login?userName=elon&password=elon123
2) http://localhost:8081/logout
3) http://localhost:8081/checkBalance
4) http://localhost:8081/withdraw?50=3&100=3&200=1&500=3&2000=2
5) http://localhost:8081/deposit?50=3&100=3&200=1&500=3&2000=2
