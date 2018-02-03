using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using FCMServer.Controllers;
using FCMServer.Model;
using System.Net;
using System.Text;
using System.IO;

namespace FCMServer.View
{
    public partial class Admin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSend_Click(object sender, EventArgs e)
        {
            FCMController fcmController = new FCMController();
            List<FCM> dsFcm = fcmController.getFCMs();
            WebRequest tRequest;
            //Thiết lập FCM send;
            tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");
            tRequest.Method = "POST";
            tRequest.UseDefaultCredentials = true;

            tRequest.PreAuthenticate = true;

            tRequest.Credentials = CredentialCache.DefaultNetworkCredentials;

            //Định dạng JSON
            tRequest.ContentType = "application/json";
            tRequest.Headers.Add(string.Format("Authorization: key={0}", "AAAA-sAD9sk:APA91bGIsoDQNHftnaygNre2wahbNlBzwG6rbCpx-E0VOjGDHLwSMimwxEm69I61IEmS1j8N0V3z1TCilGBn-4fJlxM8KBvI04Oh4Ew_SLE_qKsTlkGJCo6B4ZxIG6UpPtwkmnAltICW"));
            tRequest.Headers.Add(string.Format("Sender: id={0}", "1076963309257"));

            string[] arrRegid = dsFcm.Select(x => x.Token).ToArray();
            string RegArr = string.Empty;
            RegArr = string.Join("\",\"", arrRegid);

            string postData = "{ \"registration_ids\": [ \"" + RegArr + "\" ],\"data\": {\"message\": \"" + txtNotification.Text + "\",\"body\": \"" + txtNotification.Text + "\",\"title\": \"" + txtTitle.Text + "\",\"collapse_key\":\"" + txtNotification.Text + "\"}}";

            Byte[] byteArray = Encoding.UTF8.GetBytes(postData);
            tRequest.ContentLength = byteArray.Length;

            Stream dataStream = tRequest.GetRequestStream();
            dataStream.Write(byteArray, 0, byteArray.Length);
            dataStream.Close();

            WebResponse tResponse = tRequest.GetResponse();

            dataStream = tResponse.GetResponseStream();

            StreamReader tReader = new StreamReader(dataStream);

            String sResponseFromServer = tReader.ReadToEnd();

            txtResult.Text = sResponseFromServer; //Lấy thông báo kết quả từ FCM server.
            tReader.Close();
            dataStream.Close();
            tResponse.Close();
        }
    }
}