package dvp.lib.ytube

import android.util.Xml
import com.soywiz.krypto.sha1
import io.ktor.network.tls.extensions.*
import io.ktor.util.*
import org.junit.Test
import java.util.Date

class CryptoTest {

    @Test
    fun generateSidAuth(){
//        CONSENT=YES+cb;
//        YSC=DwKYllHNwuw;
//        SAPISID=YGwampI668Ld6fEN/AyIwyAPa8hxTZi-7i;
//        __Secure-3PAPISID=YGwampI668Ld6fEN/AyIwyAPa8hxTZi-7i;
//        __Secure-3PSID=TwhMu_gT0JV5naQRnQTG2eZZqfhyD4Ynbw75MGM_KU7ib_kRmJNxU6Nt8oCqVHKpMKmpMQ.;
//        set-cookie: SMSV=ADHTe-AByCcWbvkggdNDYtqnviMZS-ylbaSl1dkhM18JZb0GtYzIm0IfJJz2ilzWxUmXG0kuA-Xi-wTk2O_HJJuyLRW_c20PPjr4vgdA-1yVF5gbKP-ATZg;Path=/;Expires=Wed, 20-Apr-2033 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH
//        set-cookie: SID=TwiSxc-K2K0BTo44cag1JSLC69GsgTNlCzF6dfnsB-57lMeiWq619VsPqw_2FR-oOtJ1yw.;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Priority=HIGH
//        set-cookie: __Secure-1PSID=TwiSxc-K2K0BTo44cag1JSLC69GsgTNlCzF6dfnsB-57lMeiZkeC_TgawxieIHf9a3DYqA.;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH;SameParty
//        set-cookie: __Secure-3PSID=TwiSxc-K2K0BTo44cag1JSLC69GsgTNlCzF6dfnsB-57lMeiUCBkpFSNUOYou710rCQvUA.;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH;SameSite=none
//        set-cookie: LSID=TwiSxUkHofsosRcde-OyBjUDxJnJhZ7siki4BhwCVAVyS1A34jP6zud9vqh8OLOh512ceQ.;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH
//        set-cookie: __Host-1PLSID=TwiSxUkHofsosRcde-OyBjUDxJnJhZ7siki4BhwCVAVyS1A3FrN7GN0PLCLNQ_nrMGL47w.;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH;SameParty
//        set-cookie: __Host-3PLSID=TwiSxUkHofsosRcde-OyBjUDxJnJhZ7siki4BhwCVAVyS1A3cTpTI5sAqM28TOa0eEwGEg.;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH;SameSite=none
//        set-cookie: HSID=AA2ZPZFgVMoSdNxFd;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;HttpOnly;Priority=HIGH
//        set-cookie: SSID=A0faSZGMfYGetKk9L;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH
//        set-cookie: APISID=BkouC_eDCQatN-Rr/A9meCnmOuD7LGQiEg;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Priority=HIGH
//        set-cookie: SAPISID=6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;Priority=HIGH
//        set-cookie: __Secure-1PAPISID=6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;Priority=HIGH;SameParty
//        set-cookie: __Secure-3PAPISID=6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk;Domain=.google.com;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;Priority=HIGH;SameSite=none
//        set-cookie: ACCOUNT_CHOOSER=AFx_qI5Lp1gFaIIF99TMLKrPG4OEU3S7dZvMxGI1z0p7esurHHX3yYZodDZqjhOUKF69UmfTr0dOAF2H9ONEBYk2Tl9CsJww-Z7l2xGE-NmHRCrkMWHEMXQ;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH
//        set-cookie: __Host-GAPS=1:mMyAdDxoijCcxl7RkndI-B7cXXrckb7aJqPqN7Kc04-DhUKYvh-RTjpp6Q-xdcr2Fc5PPlRR-LRgajd7LcLAbUTSVMcwyg:Tg6X1tN9JI-qjIYW;Path=/;Expires=Fri, 21-Feb-2025 05:24:04 GMT;Secure;HttpOnly;Priority=HIGH
//        set-cookie: NID=511=evb5K1H1FJWQbV-7m8lmMUk6Jw7zPlUEZgwQ5aChPhTWPkZIJ0voBZGWULRnfrZydXuqmoL8ovf8W4POVWgcmJVHTGkv84tHpM-JExAtztSwFXl_I9f9Gpl8eKOBqYaK5CBdBPbLnqSD-BztHJeZQZzRlA-CFGAnvU0Qf-ni5qQeEPEBxVF2rQlGRTEGLjqFzTr3Xbx8ZL3bQHP4VlmKhB8UFrRT; expires=Thu, 24-Aug-2023 05:24:04 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=none     val timestamp = System.currentTimeMillis()/1000

//        val cookie = "Cookie" to "SID=${credentials.SID}; HSID=${credentials.HSID}; SSID=${credentials.SSID}; APISID=${credentials.APISID}; SAPISID=${credentials.SAPISID};"
        val cookieStr = "CONSENT=YES+cb; YSC=DwKYllHNwuw; SAPISID=6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk; __Secure-3PAPISID=6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk; __Secure-3PSID=TwiSxc-K2K0BTo44cag1JSLC69GsgTNlCzF6dfnsB-57lMeiUCBkpFSNUOYou710rCQvUA.;"
        val timestamp = System.currentTimeMillis()/1000
        val sid = "6Erg-t2NzgWQWLxk/Akk4nGXAQEwTUevJk"
        val origin = "https://www.youtube.com"

        val sidBytes = "$timestamp $sid $origin".encodeToByteArray()
        val sidHash = sidBytes.sha1()
        val sidHashBase64 = sidHash.hexLower
        val ret = "${timestamp}_${sidHashBase64}"
        println(ret)
    }
}