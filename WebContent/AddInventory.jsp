<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <center>
        <h1>Add Item</h1>
        <form name="AddItemServlet">
        <table>
            <tr>
                <td align="right">Name :</td>
                <td><input type="text" name="itemName"></td>
            </tr>
            <tr>
                <td align="right">Price :</td>
                <td><input type="text" name="itemPrice"></td>
            </tr>
            <tr>
                <td align="right">Quantity :</td>
                <td><input type="text" name="itemQuantity"></td>
            </tr>
            <tr>
                <td align="right">Description : </td>
                <td><input type="text" name="itemDescription"></td>
            </tr>
            <tr>
                <td align="right">Image URL : </td>
                <td><input type="text" name="itemImageURL"></td>
            </tr>
            <tr>
                <td align="right">Platform :</td>
                <td><select name="itemPlatform"></select></td>
            </tr>
            <tr>
                <td align="right">Type :</td>
                <td><select name="itemType"></select></td>
            </tr>
            <tr>
                <td colspan="2" align="middle">
                    <input  type="submit" value="Add Item">             
                </td>
            </tr>
        </table>
        </form>
    </center>
    </body>
</html>