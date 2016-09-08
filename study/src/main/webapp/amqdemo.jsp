<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/amq_jquery_adapter.js"></script>
<script type="text/javascript" src="js/amq.js"></script>
<script type="text/javascript">
  var amq = org.activemq.Amq;
  amq.init({ 
    uri: 'amq', 
    logging: true,
    timeout: 20
  });
  var myHandler =
  {
    rcvMessage: function(message)
    {
       alert(message);
    }
  };

  amq.addListener("01","topic://testQueue",myHandler.rcvMessage);
 
  function send() {
	 	var msg =  $("#message").val();
	  amq.sendMessage("topic://testQueue",msg);
  }
</script>
</head>
<body>
	<input type="text" name = "message" id="message">
	<button onclick="send()">发送</button>
</body>
</html>