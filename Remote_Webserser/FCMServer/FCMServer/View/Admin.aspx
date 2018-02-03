<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Admin.aspx.cs" Inherits="FCMServer.View.Admin" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        Input title of notification:<br />
        <asp:TextBox ID="txtTitle" runat="server" Width="300px"></asp:TextBox>
        <br />
        Input notification:<br />
        <asp:TextBox ID="txtNotification" runat="server" Height="180px" TextMode="MultiLine" Width="300px"></asp:TextBox>
        <br />
        <asp:Button ID="btnSend" runat="server" OnClick="btnSend_Click" Text="Send notification to all customers" />
        <br />
        Result:<br />
        <asp:TextBox ID="txtResult" runat="server" Height="180px" TextMode="MultiLine" Width="300px"></asp:TextBox>
        <br />
    </form>
</body>
</html>
