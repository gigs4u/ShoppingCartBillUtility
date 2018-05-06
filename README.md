# My Shopping Cart Bill Generation Utility (Spring Boot Rest API)

# ========== Problem Statement ========== 
Build a RESTful service that implments a check out counter for an online retail store that scans products and generates an itemized bill.
The bill should also total the cost of all the products and the applicable sales tax for each product.
The total cost and total sales tax should be printed
Sales tax varies based on the type of products
- category A products carry a levy of 10%
- category B products carry a levy of 20%
- category C products carry no levy

# ========== Solution ========== 

**Note for testing purpose I have made all the GET Rest API.

# Step-1: Deploy & Run My Shopping Cart Billing Utility (Spring Boot Rest API)

- Clone GIT Repository locally using this URL : https://github.com/gigs4u/ShoppingCartBillUtility
- Option 1: Import Maven Project in eclipse. Right click on project and Run as Java Application.
- Option 2: Open command Prompt and run "mvn package" command in the folder where you have cloned the GIT repository.
	Once mvn package is completed open the folder ..\ShoppingCartCheckOut\target\ and run 
	java -jar shoppingCartCheckOut-0.0.1-SNAPSHOT.jar

- Open browser and type this URL to see Greetings REST services exposed : http://localhost:8080/?name=Paresh

# Step-2: Create New Bill Id

http://localhost:8080/bill/

It displays the BillId
BillId=1525529321998
Copy the BillId

# Step-3: Fetch bill Details - 1525529321998 should be replaced by Bill Id generated in Step-2
http://localhost:8080/bill/1525529321998

Response - {"prodList":[],"billId":1525529321998,"billDate":"2018-05-05","totalBillAmount":0.0,"totalBillTaxAmount":0.0,"totalBillAmountWithTax":0.0,"billStatus":"CREATED"}

# Step-4: Adding Product code "1" to bill- 1525529321998 which should be replaced by Bill Id generated in Step-2. Note By default quaantity added is 1 if not specified
http://localhost:8080/bill/1525529321998/addProductToCart?scannedProdId=1
This will add Product 1 with Quantity =1 

Response - {"prodList":[{"product":{"prodId":1,"productName":"Product1","prodPrice":20.0,"taxCategory":{"categoryName":"A","taxRate":10.0}},"prodQty":1,"taxAmt":2.0,"totalPrice":20.0}],"billId":1525529321998,"billDate":"2018-05-05","totalBillAmount":20.0,"totalBillTaxAmount":2.0,"totalBillAmountWithTax":22.0,"billStatus":"INPROGRESS"}

# Step-5: Adding Product code "2" to bill - 1525529321998 which should be replaced by Bill Id generated in Step-2. Here we would add multiple quantity of product by using quantity Request Param.

Here productId=2 and quantity=2
http://localhost:8080/bill/1525529321998/addProductToCart?scannedProdId=2&quantity=2
Response -
{"prodList":[{"product":{"prodId":2,"productName":"Product2","prodPrice":10.0,"taxCategory":{"categoryName":"B","taxRate":20.0}},"prodQty":2,"taxAmt":4.0,"totalPrice":20.0},{"product":{"prodId":1,"productName":"Product1","prodPrice":20.0,"taxCategory":{"categoryName":"A","taxRate":10.0}},"prodQty":1,"taxAmt":2.0,"totalPrice":20.0}],"billId":1525529321998,"billDate":"2018-05-05","totalBillAmount":40.0,"totalBillTaxAmount":6.0,"totalBillAmountWithTax":46.0,"billStatus":"INPROGRESS"}


# Step-6: Checkout bill - 1525529321998 should be replaced by Bill Id generated in Step-2
http://localhost:8080/bill/1525529321998/checkOutCart
{"prodList":[{"product":{"prodId":2,"productName":"Product2","prodPrice":10.0,"taxCategory":{"categoryName":"B","taxRate":20.0}},"prodQty":2,"taxAmt":4.0,"totalPrice":20.0},{"product":{"prodId":1,"productName":"Product1","prodPrice":20.0,"taxCategory":{"categoryName":"A","taxRate":10.0}},"prodQty":1,"taxAmt":2.0,"totalPrice":20.0}],"billId":1525529321998,"billDate":"2018-05-05","totalBillAmount":40.0,"totalBillTaxAmount":6.0,"totalBillAmountWithTax":46.0,"billStatus":"COMPLETED"}


