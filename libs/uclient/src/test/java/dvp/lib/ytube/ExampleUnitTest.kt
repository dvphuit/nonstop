package dvp.lib.ytube

import dvp.lib.ytube.apis.ChannelApi
import dvp.lib.ytube.apis.VideoApi
import dvp.lib.ytube.dto.YoutubeResponse
import dvp.lib.ytube.utils.get
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun urlTest() {
        runBlocking {
            val start = System.currentTimeMillis()
            val info = UClient().run {
                isTest = true
//                getBasicInfo("aqz-KE-bpKQ")
            }
            println("info: $info")
            println("extract end at ${System.currentTimeMillis() - start}ms")
        }
    }

    @Test
    fun getVideos() {
        val api = VideoApi()
        api.setToken("ya29.a0AVvZVsr-djs_VjH5dq9uVZV0wwJwK4YD1YUuIgDPiuwjUI0ttfk1icgq4gap6R77UFOnLjif-xkyOlNhuy64omF_4v1PL1_-PCEzrIXGjsqxQ-hzlnzEAbxIVoQGJXYaLxcVWR99zYw3km5Xv-xK7h1ACNHfqlPi0waCgYKAXUSARMSFQGbdwaIX48CEd8FOG_fNYxLrmkDEA0169")
        runBlocking {
            val res = api.list()
            println(res.bodyAsText())
        }
    }

    @Test
    fun getChannels() {
        val api = ChannelApi()
        api.setToken("ya29.a0AVvZVsogfpwinW1Ev8j28rympol2VQoFXWQ-WR-cTqSzSMs6Vzg-OmpHE4b00Ily2BJGx4_HbdDz3FI_A0HfDWJ8CZfJnf9ly8Jst9nAXk8myCkfzOHb71foE_InT_CtLURTGHgEg7V3zH67cp_UcoM4ghzQiDjgJwaCgYKARESARMSFQGbdwaIwue56tjdvujoiuVPLdGinQ0169")
        val videos = Json.parseToJsonElement(videoListJson)["items"]!!.jsonArray
        val channelIds = videos.map { it["snippet"]["channelId"]!!.jsonPrimitive.content }
        runBlocking {
            val channels = api.list(channelIds)
            println(channels)
        }
    }

    @Test
    fun mergeVideosAndChannels() {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val videoRes = json.decodeFromString(YoutubeResponse.serializer(), videoListJson)
        val channelRes = json.decodeFromString(YoutubeResponse.serializer(), channelListJson)

        println(videoRes)
        println(channelRes)
    }
}


private val videoListJson = """
{
  "kind": "youtube#videoListResponse",
  "etag": "vw1AU8SI6ZX_4DwsoxSBGgrwGHA",
  "items": [
    {
      "kind": "youtube#video",
      "etag": "hffttYZBbYUB37JdnKP4LFjT9Jw",
      "id": "ymqZKP8cO6A",
      "snippet": {
        "publishedAt": "2023-02-19T14:15:09Z",
        "channelId": "UCFMEYTv6N64hIL9FlQ_hxBw",
        "title": "2 Ngày 1 Đêm Việt Nam|Tập 30: Kiều Minh Tuấn bị tấn công dồn dập, HIEUTHUHAI \"lộ body\" giữa đêm đông",
        "description": "2 Ngày 1 Đêm Việt Nam|Tập 30: Kiều Minh Tuấn bị tấn công dồn dập, HIEUTHUHAI \"lộ body\" giữa đêm đông\n\n☀️Tiếp tục hành trình #2Ngày1Đêm tại Mộc Châu - Sơn La, dàn cast sẽ phải vượt qua thử thách chinh phục độ cao và thử thách may rủi. Thành viên nào sẽ được ăn sướng trong tập tuần này?\n\n⛺ 2 Ngày 1 Đêm là một chương trình truyền hình thực tế, nơi các nghệ sĩ có những chuyến đi đến nhiều vùng miền khác nhau khắp cả nước. Các thành viên phải hoàn thành nhiều nhiệm vụ và thử thách khác nhau để nhận những phần thưởng hay hình phạt thú vị.\n\n▶️Xem sớm nhất và trọn bộ trên VieON 👉 https://vieon.vn/2-ngay-1-dem-tu-do-tu-lo.html\n📺 Đón xem 2 NGÀY 1 ĐÊM vào lúc 20H30 Chủ Nhật hàng tuần trên HTV7 và phát lại trên kênh \n@dongtaypromotionofficial \n🎬 Xem thêm Hậu trường siêu lầy lội của 2 Ngày 1 Đêm trên kênh @2NGAY1DEM  \n\n#2Ngày1Đêm #2Ngày1ĐêmViệtNam #2Days1NightVietNam #2N1Đ #Tập30 #DongTayPromotion\n#TrườngGiang #KiềuMinhTuấn #LêDươngBảoLâm #NgôKiếnHuy #CrisPhan #HIEUTHUHAI\n\n📣 Subscribe kênh ĐÔNG TÂY PROMOTION OFFICIAL để không bỏ lỡ các chương trình SIÊU HẤP DẪN: https://bit.ly/SubDTP\n📣 Subscribe kênh DONG TAY ENTERTAINMENT để xem các VIDEO HOT từ các show đỉnh nhất: https://bit.ly/SubDTE\n\n⏩ Bí mật HẬU TRƯỜNG: https://bit.ly/HAUTRUONGDTP \n⏩ SHOW HAY mỗi ngày: https://bit.ly/showhotDTP \n\n👉 CÁC SHOW HOT KHÁC: \n2 Ngày 1 Đêm Việt Nam: https://bit.ly/2Ngay1DemVN\n7 Nụ Cười Xuân: https://bit.ly/7NCX_Mua6\nƠn Giời Cậu Đây Rồi: https://bit.ly/OnGioiCauDayRoiMua8\nNhanh Như Chớp: https://bit.ly/NhanhNhuChop4\nKý Ức Vui Vẻ: https://bit.ly/KYUCVUIVEMUA4\n\n👉 Theo dõi các kênh chính thức của Dong Tay Promotion - thuộc sở hữu của DatVietVAC tại:\n► Fanpage: https://www.facebook.com/DongTayPromotion\n► Instagram: https://www.instagram.com/dongtaypromotion.official/\n► TikTok: https://www.tiktok.com/@dongtaypromotionofficial",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/ymqZKP8cO6A/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/ymqZKP8cO6A/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/ymqZKP8cO6A/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/ymqZKP8cO6A/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/ymqZKP8cO6A/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "ĐÔNG TÂY PROMOTION OFFICIAL",
        "tags": [
          "dong tay promotion",
          "đông tây promotion",
          "htv",
          "tvshow",
          "gameshow",
          "2 ngày 1 đêm",
          "2 ngay 1 dem",
          "2 ngày 1 đêm việt nam",
          "2 ngay 1 dem viet nam",
          "2 days 1 night",
          "2N1Đ",
          "chương trình thực tế 2022",
          "trường giang",
          "truong giang",
          "kiều minh tuấn",
          "kieu minh tuan",
          "cris phan",
          "dương lâm",
          "lê dương bảo lâm",
          "ngô kiến huy",
          "ngo kien huy",
          "hieuthuhai",
          "2 ngày 1 đêm tập 30",
          "2 ngay 1 dem tap 30",
          "tập 30 2 ngày 1 đêm",
          "tap 30 2 ngay 1 dem",
          "2 ngày 1 đêm full"
        ],
        "categoryId": "24",
        "liveBroadcastContent": "none",
        "defaultLanguage": "vi",
        "localized": {
          "title": "2 Ngày 1 Đêm Việt Nam|Tập 30: Kiều Minh Tuấn bị tấn công dồn dập, HIEUTHUHAI \"lộ body\" giữa đêm đông",
          "description": "2 Ngày 1 Đêm Việt Nam|Tập 30: Kiều Minh Tuấn bị tấn công dồn dập, HIEUTHUHAI \"lộ body\" giữa đêm đông\n\n☀️Tiếp tục hành trình #2Ngày1Đêm tại Mộc Châu - Sơn La, dàn cast sẽ phải vượt qua thử thách chinh phục độ cao và thử thách may rủi. Thành viên nào sẽ được ăn sướng trong tập tuần này?\n\n⛺ 2 Ngày 1 Đêm là một chương trình truyền hình thực tế, nơi các nghệ sĩ có những chuyến đi đến nhiều vùng miền khác nhau khắp cả nước. Các thành viên phải hoàn thành nhiều nhiệm vụ và thử thách khác nhau để nhận những phần thưởng hay hình phạt thú vị.\n\n▶️Xem sớm nhất và trọn bộ trên VieON 👉 https://vieon.vn/2-ngay-1-dem-tu-do-tu-lo.html\n📺 Đón xem 2 NGÀY 1 ĐÊM vào lúc 20H30 Chủ Nhật hàng tuần trên HTV7 và phát lại trên kênh \n@dongtaypromotionofficial \n🎬 Xem thêm Hậu trường siêu lầy lội của 2 Ngày 1 Đêm trên kênh @2NGAY1DEM  \n\n#2Ngày1Đêm #2Ngày1ĐêmViệtNam #2Days1NightVietNam #2N1Đ #Tập30 #DongTayPromotion\n#TrườngGiang #KiềuMinhTuấn #LêDươngBảoLâm #NgôKiếnHuy #CrisPhan #HIEUTHUHAI\n\n📣 Subscribe kênh ĐÔNG TÂY PROMOTION OFFICIAL để không bỏ lỡ các chương trình SIÊU HẤP DẪN: https://bit.ly/SubDTP\n📣 Subscribe kênh DONG TAY ENTERTAINMENT để xem các VIDEO HOT từ các show đỉnh nhất: https://bit.ly/SubDTE\n\n⏩ Bí mật HẬU TRƯỜNG: https://bit.ly/HAUTRUONGDTP \n⏩ SHOW HAY mỗi ngày: https://bit.ly/showhotDTP \n\n👉 CÁC SHOW HOT KHÁC: \n2 Ngày 1 Đêm Việt Nam: https://bit.ly/2Ngay1DemVN\n7 Nụ Cười Xuân: https://bit.ly/7NCX_Mua6\nƠn Giời Cậu Đây Rồi: https://bit.ly/OnGioiCauDayRoiMua8\nNhanh Như Chớp: https://bit.ly/NhanhNhuChop4\nKý Ức Vui Vẻ: https://bit.ly/KYUCVUIVEMUA4\n\n👉 Theo dõi các kênh chính thức của Dong Tay Promotion - thuộc sở hữu của DatVietVAC tại:\n► Fanpage: https://www.facebook.com/DongTayPromotion\n► Instagram: https://www.instagram.com/dongtaypromotion.official/\n► TikTok: https://www.tiktok.com/@dongtaypromotionofficial"
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT1H44M18S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "3544832",
        "likeCount": "48602",
        "favoriteCount": "0",
        "commentCount": "3239"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "gfcw1SmmVYJ4zXmlREhBH82kWLs",
      "id": "IOe0tNoUGv8",
      "snippet": {
        "publishedAt": "2023-02-09T13:00:09Z",
        "channelId": "UCVIIa6OL-FautUqhHjAoz_A",
        "title": "EM ĐỒNG Ý (I DO) - ĐỨC PHÚC x 911 x KHẮC HƯNG | OFFICIAL MUSIC VIDEO | VALENTINE 2023",
        "description": "EM ĐỒNG Ý (I DO) - ĐỨC PHÚC x 911 x KHẮC HƯNG | OFFICIAL MUSIC VIDEO | VALENTINE 2023\n#911 #ducphuc #emdongy \nLinkfire: https://smevn.lnk.to/EDY-DP\nSpotify: https://smevn.lnk.to/EDY-DP/spotify\nApple Music: https://smevn.lnk.to/EDY-DP/applemusic\niTunes: https://smevn.lnk.to/EDY-DP/itunes\nZing Mp3: https://smevn.lnk.to/EDY-DP/zingmp3\nNCT: https://smevn.lnk.to/EDY-DP/nct\n\nSinger: DUC PHUC X 911\nComposed By: JOHN MCLAUGHLIN / JUD MAHONEY / LEE BRENNAN / JIMMY CONSTABLE / NATALIE DELUCIA\nVietnamese Lyric: KHAC HUNG\nMix/Mastering: KHAC HUNG\n\n\nExecutive Producer: THREAD & NEEDLE  - AMO - DUC PHUC  ENTERTAINMENT - 911\nProject Manager: GIANG DO - HONG NHUNG - TRANG NHUNG\nAccount: THU NGA - NGOC ANH\n \nPr Manager: DUC PHUC ENTERTAINMENT - NGUYEN BONG - MY HANH  \n\nMv Produced By Cinemout Director: NGUYEN QUANG HUY\nProducer: THUY TRANG, NGUYEN QUANG HUY\nD.O.P: THÀNH THINN F.N.P\nCam A/Steadicam: TUAN MINH, HUY CHOE\nCam B: KIEN NGUYEN, VIET VU\nSpotter: HOANG MINH, VU HOANG\nFlycam: DAT.DRONES\nGaffer: TUAN TAN\n\nArt Director: LAN ZI\nAssistant Art Director: NGUYEN LAM XUAN TRANG\nProps Master & Set Decorator: NGUYEN KHAC HOP TEAM (7ART.CREW)\n\n1st Assistant Director: NGUYEN ANH KHOA  (NAKEI)\n2nd Assistant Director: JOCELYN PHAN CHOREOGRAPHER: LINH AN (TDV)\nProduction assistant: TUONG VY, TRUNG KIEN, MI THAO NGUYEN, TRANG CHECC, VU TIEN DUNG\n\nTalent: THU AN - HONG SON MINH TAM - DUC HOANG - PHUC LONG (MON) CHI PHAM - ANH TUAN\n\nDuc Phuc Team:\nStylist: KHUC MANH QUAN\nMake-up: KYO PHAN\nHair Stylist: NGUYEN TO\nPhoto: MANH BI\nDesign: DINH THIEN PHU\n\nStylist: NHUNG TRAN TEAM\nMakeup And Hair: ELLY ELLY TEAM BTS: HA MANH HOANG, AN NGUYEN GIA  \nEditor: NGUYEN QUANG HUY\nColorist: TIEU PHAM (COLOUR.MATRIX)\n\n\nCamera & Lighting Equipment: TL EQUIPMENT CATERING: HBM CATERING\n\nTHANKS TO\nSponsors: SAMSUNG / SUNWORLD / PNJ / VIETNAM AIRLINES\nPartnership: HAND & HEART / ADAM STORE / COLORIZED / ELEGANT SUITES WESTLAKE HANOI\nMedia Partners: SONY MUSIC / METUB / KIND MUSIC / BOX VIETNAM / ĐÀI PHÁT THANH / CỔ ĐỘNG\n\nTheo dõi Đức Phúc tại :\nYouTube : https://metub.net/ducphuc\nFacebook: https://www.facebook.com/ola.nguyen.1\nFanpage: https://www.facebook.com/ducphuc.info/\nInstagram: https://www.instagram.com/ngxducphuc_/",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/IOe0tNoUGv8/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/IOe0tNoUGv8/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/IOe0tNoUGv8/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/IOe0tNoUGv8/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/IOe0tNoUGv8/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "ĐỨC PHÚC OFFICIAL",
        "tags": [
          "đức phúc",
          "em đồng ý",
          "duc phuc",
          "em đồng ý đức phúc",
          "em dong y",
          "em dong y duc phuc",
          "đức phúc em đồng ý",
          "đồng ý",
          "đức phúc official",
          "valentine đức phúc",
          "em đồng ý i do",
          "em đồng ý mv",
          "đức phúc 911",
          "duc phuc 911",
          "em đồng ý đức phúc 911",
          "em dong y duc phuc 911",
          "mv em đồng ý đức phúc 911",
          "em đồng ý lyrics",
          "em dong y 911",
          "mv duc phuc 911",
          "ĐỨC PHÚC x 911 x KHẮC HƯNG",
          "i do",
          "i do 911",
          "i do lyrics",
          "911 band",
          "911 i do",
          "i do 911 live",
          "i do 911 nightcore",
          "i do duc phuc 911"
        ],
        "categoryId": "10",
        "liveBroadcastContent": "none",
        "defaultLanguage": "en",
        "localized": {
          "title": "EM ĐỒNG Ý (I DO) - ĐỨC PHÚC x 911 x KHẮC HƯNG | OFFICIAL MUSIC VIDEO | VALENTINE 2023",
          "description": "EM ĐỒNG Ý (I DO) - ĐỨC PHÚC x 911 x KHẮC HƯNG | OFFICIAL MUSIC VIDEO | VALENTINE 2023\n#911 #ducphuc #emdongy \nLinkfire: https://smevn.lnk.to/EDY-DP\nSpotify: https://smevn.lnk.to/EDY-DP/spotify\nApple Music: https://smevn.lnk.to/EDY-DP/applemusic\niTunes: https://smevn.lnk.to/EDY-DP/itunes\nZing Mp3: https://smevn.lnk.to/EDY-DP/zingmp3\nNCT: https://smevn.lnk.to/EDY-DP/nct\n\nSinger: DUC PHUC X 911\nComposed By: JOHN MCLAUGHLIN / JUD MAHONEY / LEE BRENNAN / JIMMY CONSTABLE / NATALIE DELUCIA\nVietnamese Lyric: KHAC HUNG\nMix/Mastering: KHAC HUNG\n\n\nExecutive Producer: THREAD & NEEDLE  - AMO - DUC PHUC  ENTERTAINMENT - 911\nProject Manager: GIANG DO - HONG NHUNG - TRANG NHUNG\nAccount: THU NGA - NGOC ANH\n \nPr Manager: DUC PHUC ENTERTAINMENT - NGUYEN BONG - MY HANH  \n\nMv Produced By Cinemout Director: NGUYEN QUANG HUY\nProducer: THUY TRANG, NGUYEN QUANG HUY\nD.O.P: THÀNH THINN F.N.P\nCam A/Steadicam: TUAN MINH, HUY CHOE\nCam B: KIEN NGUYEN, VIET VU\nSpotter: HOANG MINH, VU HOANG\nFlycam: DAT.DRONES\nGaffer: TUAN TAN\n\nArt Director: LAN ZI\nAssistant Art Director: NGUYEN LAM XUAN TRANG\nProps Master & Set Decorator: NGUYEN KHAC HOP TEAM (7ART.CREW)\n\n1st Assistant Director: NGUYEN ANH KHOA  (NAKEI)\n2nd Assistant Director: JOCELYN PHAN CHOREOGRAPHER: LINH AN (TDV)\nProduction assistant: TUONG VY, TRUNG KIEN, MI THAO NGUYEN, TRANG CHECC, VU TIEN DUNG\n\nTalent: THU AN - HONG SON MINH TAM - DUC HOANG - PHUC LONG (MON) CHI PHAM - ANH TUAN\n\nDuc Phuc Team:\nStylist: KHUC MANH QUAN\nMake-up: KYO PHAN\nHair Stylist: NGUYEN TO\nPhoto: MANH BI\nDesign: DINH THIEN PHU\n\nStylist: NHUNG TRAN TEAM\nMakeup And Hair: ELLY ELLY TEAM BTS: HA MANH HOANG, AN NGUYEN GIA  \nEditor: NGUYEN QUANG HUY\nColorist: TIEU PHAM (COLOUR.MATRIX)\n\n\nCamera & Lighting Equipment: TL EQUIPMENT CATERING: HBM CATERING\n\nTHANKS TO\nSponsors: SAMSUNG / SUNWORLD / PNJ / VIETNAM AIRLINES\nPartnership: HAND & HEART / ADAM STORE / COLORIZED / ELEGANT SUITES WESTLAKE HANOI\nMedia Partners: SONY MUSIC / METUB / KIND MUSIC / BOX VIETNAM / ĐÀI PHÁT THANH / CỔ ĐỘNG\n\nTheo dõi Đức Phúc tại :\nYouTube : https://metub.net/ducphuc\nFacebook: https://www.facebook.com/ola.nguyen.1\nFanpage: https://www.facebook.com/ducphuc.info/\nInstagram: https://www.instagram.com/ngxducphuc_/"
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT3M42S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "true",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "16220232",
        "likeCount": "375646",
        "favoriteCount": "0",
        "commentCount": "13842"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "mUpjlMMaGTI5G1FrvAlLK6A1-po",
      "id": "uYzVK8G53bk",
      "snippet": {
        "publishedAt": "2023-02-16T20:21:35Z",
        "channelId": "UC4LvrpNXujjbGOS4RDvr41g",
        "title": "HIGHLIGHTS: BARCELONA - MAN UNITED | ĐÔI CÔNG GAY CẤN, RƯỢT ĐUỔI MÃN NHÃN | UEL 22/23",
        "description": "Quý khán giả đừng quên SUBSCRIBE: https://bit.ly/FPTBongda\n\nHIGHLIGHTS: BARCELONA - MAN UNITED | ĐÔI CÔNG GAY CẤN, RƯỢT ĐUỔI MÃN NHÃN | UEL 22/23\n------------------------------\nThưởng thức các trận cầu đỉnh cao tại hệ thống kênh Youtube của FPT\n️⚽ FPT Bóng Đá: https://bit.ly/FPTBongda\n⚽ FPT Bóng Đá Việt: https://bit.ly/FPTBongDaViet\n⚽ FPT Bóng Rổ: https://bit.ly/FPTBongro\n------------------------------\n© Bản quyền thuộc về FPT Bóng Đá và Đối Tác\n© Copyright by FPT Bóng Đá and Partner☞ Do not Reup\n© Copyright all rights reserved\n------------------------------\n#Barcelona #ManchesterUnited #EuropaLeague #FPTBóngĐá #BongDaChauAu #BongDa",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/uYzVK8G53bk/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/uYzVK8G53bk/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/uYzVK8G53bk/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/uYzVK8G53bk/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/uYzVK8G53bk/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "FPT Bóng Đá",
        "tags": [
          "fpt bóng đá",
          "barcelona vs mu",
          "barcelona vs manchester united",
          "barca vs mu",
          "barca vs man united",
          "barcelona vs man united",
          "barca man united",
          "barca vs man chester united",
          "barcelona",
          "manchester united",
          "mu",
          "man united",
          "europa league",
          "trực tiếp barcelona vs manchester united",
          "barcelona - man united",
          "barca - man united",
          "barcelona - mu"
        ],
        "categoryId": "17",
        "liveBroadcastContent": "none",
        "localized": {
          "title": "HIGHLIGHTS: BARCELONA - MAN UNITED | ĐÔI CÔNG GAY CẤN, RƯỢT ĐUỔI MÃN NHÃN | UEL 22/23",
          "description": "Quý khán giả đừng quên SUBSCRIBE: https://bit.ly/FPTBongda\n\nHIGHLIGHTS: BARCELONA - MAN UNITED | ĐÔI CÔNG GAY CẤN, RƯỢT ĐUỔI MÃN NHÃN | UEL 22/23\n------------------------------\nThưởng thức các trận cầu đỉnh cao tại hệ thống kênh Youtube của FPT\n️⚽ FPT Bóng Đá: https://bit.ly/FPTBongda\n⚽ FPT Bóng Đá Việt: https://bit.ly/FPTBongDaViet\n⚽ FPT Bóng Rổ: https://bit.ly/FPTBongro\n------------------------------\n© Bản quyền thuộc về FPT Bóng Đá và Đối Tác\n© Copyright by FPT Bóng Đá and Partner☞ Do not Reup\n© Copyright all rights reserved\n------------------------------\n#Barcelona #ManchesterUnited #EuropaLeague #FPTBóngĐá #BongDaChauAu #BongDa"
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT14M16S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "regionRestriction": {
          "allowed": [
            "VN"
          ]
        },
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "3290216",
        "likeCount": "26008",
        "favoriteCount": "0",
        "commentCount": "1982"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "d2zNVgxBy-sqrlJddNkbRTRxR2Y",
      "id": "Jk2oWJURllU",
      "snippet": {
        "publishedAt": "2023-02-14T12:00:13Z",
        "channelId": "UCzSl8LJLpB3LmoY18oIWrtw",
        "title": "[CÔ GIÁO BẢO NGÂN] - CÁC TÊN NÊN ĐẶT KHI ĐI HỌC",
        "description": "[CÔ GIÁO BẢO NGÂN] - CÁC TÊN KHÔNG NÊN ĐẶT KHI ĐI HỌC P3\n——————————\nSubscribe kênh của mình để xem thêm nhiều video thú vị hơn nhé.\nĐăng ký kênh tại: https://metub.net/baongan\n\n\nTheo dõi mình trên:\n\nTiktok: https://www.tiktok.com/@ngan.549\nFacebook: https://www.facebook.com/quy.549\nEmail: thichche209@gmail.com\n\n#baongan #quy #cogiaoquy #tiktok #quyngan #nhacche #tiktok #thichche #parody #shorts \n---------------------/---------------------\nChannel Development: Metub Network\n---------------------/---------------------\n© Copyright by Quỷ - Do Not Reup ©",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/Jk2oWJURllU/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/Jk2oWJURllU/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/Jk2oWJURllU/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/Jk2oWJURllU/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/Jk2oWJURllU/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Bảo Ngân",
        "tags": [
          "quỷ",
          "quỷ ngân",
          "quy549",
          "tiktok",
          "tik tok",
          "quy tiktok",
          "quỷ tiktok",
          "bảo ngân",
          "bao ngan",
          "bảo ngân tiktok",
          "nhạc chế",
          "chế nhạc",
          "nhạc chế tiktok",
          "nhac che",
          "parody",
          "nhac che parody",
          "nhac che hay",
          "thích chế",
          "các kiểu giáo viên",
          "cô giáo quỷ",
          "học sinh",
          "hài tiktok",
          "tiktok hài",
          "comedy",
          "funny",
          "đi học",
          "chuyện đi học",
          "đi học kiểu",
          "shorts",
          "cô giáo gen z",
          "cô giáo hài hước",
          "cô giáo 4.0",
          "giáo viên 4.0"
        ],
        "categoryId": "24",
        "liveBroadcastContent": "none",
        "defaultLanguage": "vi",
        "localized": {
          "title": "[CÔ GIÁO BẢO NGÂN] - CÁC TÊN NÊN ĐẶT KHI ĐI HỌC",
          "description": "[CÔ GIÁO BẢO NGÂN] - CÁC TÊN KHÔNG NÊN ĐẶT KHI ĐI HỌC P3\n——————————\nSubscribe kênh của mình để xem thêm nhiều video thú vị hơn nhé.\nĐăng ký kênh tại: https://metub.net/baongan\n\n\nTheo dõi mình trên:\n\nTiktok: https://www.tiktok.com/@ngan.549\nFacebook: https://www.facebook.com/quy.549\nEmail: thichche209@gmail.com\n\n#baongan #quy #cogiaoquy #tiktok #quyngan #nhacche #tiktok #thichche #parody #shorts \n---------------------/---------------------\nChannel Development: Metub Network\n---------------------/---------------------\n© Copyright by Quỷ - Do Not Reup ©"
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT25S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "3153570",
        "likeCount": "109428",
        "favoriteCount": "0",
        "commentCount": "152"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "hdiPcLPORGswLktK-OZO7q5Hdgs",
      "id": "UUj9zizokOA",
      "snippet": {
        "publishedAt": "2023-02-18T12:00:30Z",
        "channelId": "UCmkXtw92W4G5NZMRr72otiw",
        "title": "Đồ ăn nào an toàn cho răng? | Lạc Việt Intech Implant #lvnw #short #metub",
        "description": "Bác sĩ tư vấn 1-1 giải đáp miễn phí về trồng răng implant và các vấn đề răng miệng.\n💌 Nhắn tin: https://m.me/lacvietintechimplant\nCộng đồng “ Hội Những Người Trồng Răng Implant”\nhttps://www.facebook.com/groups/trong...\n----------------------\nNHA KHOA LẠC VIỆT INTECH - DẪN ĐẦU CHẤT LƯỢNG ĐIỀU TRỊ\n☎ Hotline:  0971.066.726\n💻 Website: https://lacvietdental.vn\n🏠 Địa chỉ:\n- Trụ sở Vinh: Số 22 Cao Thắng, P. Hồng Sơn, TP Vinh, Nghệ An\n- Trụ sở Hải Phòng: 107 Tô Hiệu, Lê Chân, TP Hải Phòng\n- Số 160 Tây Sơn, Đống Đa, Hà Nội\n#lvnw #lacvietintech #Implant #nhakhoa \n\nCopyrights 2022 ⓒ Lac Viet Network",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/UUj9zizokOA/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/UUj9zizokOA/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/UUj9zizokOA/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/UUj9zizokOA/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/UUj9zizokOA/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Chia Sẻ Kiến Thức Implant",
        "tags": [
          "implant",
          "trồng răng implant",
          "nha khoa",
          "cấy răng implant",
          "làm răng implant",
          "cấy ghép implant",
          "nha khoa Lạc Việt",
          "công nghệ implant",
          "phương pháp implant",
          "kĩ thuật implant",
          "nha khoa lạc việt",
          "trồng implant toàn hàm",
          "dental implant",
          "implantology",
          "chăm sóc răng",
          "implant nha khoa",
          "implant răng cửa",
          "implant toàn hàm",
          "implant răng",
          "trồng răng",
          "cấy răng",
          "cấy implant có đau không",
          "răng",
          "đồ ăn",
          "niềng răng ăn gì",
          "ăn",
          "tiktok",
          "đồ ăn nào an toàn cho răng",
          "niềng răng",
          "múa lân",
          "tony tv"
        ],
        "categoryId": "22",
        "liveBroadcastContent": "none",
        "localized": {
          "title": "Đồ ăn nào an toàn cho răng? | Lạc Việt Intech Implant #lvnw #short #metub",
          "description": "Bác sĩ tư vấn 1-1 giải đáp miễn phí về trồng răng implant và các vấn đề răng miệng.\n💌 Nhắn tin: https://m.me/lacvietintechimplant\nCộng đồng “ Hội Những Người Trồng Răng Implant”\nhttps://www.facebook.com/groups/trong...\n----------------------\nNHA KHOA LẠC VIỆT INTECH - DẪN ĐẦU CHẤT LƯỢNG ĐIỀU TRỊ\n☎ Hotline:  0971.066.726\n💻 Website: https://lacvietdental.vn\n🏠 Địa chỉ:\n- Trụ sở Vinh: Số 22 Cao Thắng, P. Hồng Sơn, TP Vinh, Nghệ An\n- Trụ sở Hải Phòng: 107 Tô Hiệu, Lê Chân, TP Hải Phòng\n- Số 160 Tây Sơn, Đống Đa, Hà Nội\n#lvnw #lacvietintech #Implant #nhakhoa \n\nCopyrights 2022 ⓒ Lac Viet Network"
        }
      },
      "contentDetails": {
        "duration": "PT28S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "1546449",
        "likeCount": "41082",
        "favoriteCount": "0",
        "commentCount": "45"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "55UcUU2mp4FPxmZF_qUXkGLX1AU",
      "id": "bWjSK_cfLAI",
      "snippet": {
        "publishedAt": "2023-02-02T02:42:22Z",
        "channelId": "UCSGiYwWpkUUAgqfbFj9J46A",
        "title": "What’s wrong with u bro!! 😤😤 #shorts #funnydogs",
        "description": "#funnydogs #ohno #ohnoohonohnononosong #goldenretriever #doglover #cutedog #shorts",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/bWjSK_cfLAI/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/bWjSK_cfLAI/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/bWjSK_cfLAI/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/bWjSK_cfLAI/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/bWjSK_cfLAI/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Little Golden",
        "tags": [
          "funny dog",
          "ohno",
          "funny dogs",
          "funny dog videos",
          "golden retriever",
          "cute dog",
          "shorts"
        ],
        "categoryId": "15",
        "liveBroadcastContent": "none",
        "localized": {
          "title": "What’s wrong with u bro!! 😤😤 #shorts #funnydogs",
          "description": "#funnydogs #ohno #ohnoohonohnononosong #goldenretriever #doglover #cutedog #shorts"
        }
      },
      "contentDetails": {
        "duration": "PT14S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "36905937",
        "likeCount": "885177",
        "favoriteCount": "0",
        "commentCount": "2257"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "pvXzDmoF9djp67nijtFmz0uHL9A",
      "id": "vW1boIk-g-A",
      "snippet": {
        "publishedAt": "2023-02-19T16:17:42Z",
        "channelId": "UCpnQwjzvDm1MOMtZV2zkVpA",
        "title": "HEV VS XT I SGP VS HQ I FL VS BOX I ĐẤU TRƯỜNG DANH VỌNG MÙA XUÂN 2023- VÒNG BẢNG NGÀY 19/02",
        "description": "HEV VS XT I SGP VS HQ I FL VS BOX I ĐẤU TRƯỜNG DANH VỌNG MÙA XUÂN 2023- VÒNG BẢNG NGÀY 19/02\n\nLịch thi đấu Đấu Trường Danh Vọng Mùa Xuân 2023 - Vòng Bảng ngày 19/02:\n\n👉 14h30: Heavy vs Extreme Gaming\n👉 17h00: Saigon Phantom vs HQ Esports\n👉 19h30: Team Flash vs Box Gaming\n\n🔥 Đấu Trường Danh Vọng chính thức khởi tranh mùa giải mới, 10 cái tên dù quen thuộc nhưng đội hình đã có phần thay đổi, liệu những cái tên nào sẽ giành lấy ưu thế trong chặng đầu tiên? \n\nĐừng bỏ lỡ những trận đấu hấp dẫn nhất của Đấu Trường Danh Vọng mùa Xuân 2023 diễn ra từ 16/2/2023 - 14/5/2023!!! \n#DauTruongDanhVong #TheThaoChoTheHeMoi #AOGS23 #8 #Haochuacay #Haogiaotranh #THtrueJUICEmilk #TiepNangLuongThienNhien\n\n00:00 Nhạc Chill Liên Quân\n04:35 Trước Giờ Cuồng Nhiệt\n36:48 HEV vs XT\n42:53 Ban Pick Game 1\n50:32 HEV vs XT - Game 1\n01:07:57 Ban Pick Game 2\n01:14:33 HEV vs XT - Game 2\n01:38:48 Ban Pick Game 3\n01:49:11 HEV vs XT - Game 3\n02:16:38 Phỏng Vấn Sau Trận - HEV Judas\n03:06:26 SGP vs HQ\n03:11:58 Ban Pick Game 1\n03:19:55 SGP vs HQ - Game 1\n03:43:41 Ban Pick Game 2\n03:50:58 SGP vs HQ - Game 2\n04:20:38 Ban Pick Game 3\n04:28:04 SGP vs HQ - Game 3\n04:49:03 Phỏng Vấn Sau Trận - SGP Red\n05:37:00 FL vs BOX\n05:45:03 Ban Pick Game 1\n05:54:14 FL vs BOX - Game 1\n06:23:43 Ban Pick Game 2\n06:29:13 FL vs BOX - Game 2\n07:08:00 Ban Pick Game 3\n07:15:51 FL vs BOX - Game 3\n07:37:58 Ban Pick Game 4\n07:45:52 FL vs BOX - Game 4\n08:06:22 Phỏng Vấn Sau Trận - FL 20Percent\n\n•••••••••••••••••••••••••••••••\nGarena Liên Quân Mobile & Liên Quân Mobile eSports - Garena  là 2 kênh YouTube chính thức của hội đồng Liên Quân, đồng thời là đơn vị duy nhất giữ bản quyền sản xuất toàn bộ nội dung game và giải đấu Liên Quân Mobile (Arena of Valor).",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/vW1boIk-g-A/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/vW1boIk-g-A/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/vW1boIk-g-A/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/vW1boIk-g-A/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/vW1boIk-g-A/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Liên Quân Mobile eSports-Garena",
        "tags": [
          "garena liên quân",
          "liên quân mobile",
          "liên quân",
          "arena of valor",
          "đấu trường danh vọng",
          "garena liên quân mobile",
          "aov",
          "aov vn",
          "garena",
          "DauTruongDanhVong",
          "TheThaoChoTheHeMoi",
          "AOGS23",
          "Đấu Trường Danh Vọng",
          "Thể Thao",
          "Thế Hệ Mới",
          "Thể Thao Cho Thế Hệ Mới",
          "AOG",
          "SGP",
          "VGM",
          "BOX",
          "TDT",
          "FL",
          "HEV",
          "MDH",
          "HQ",
          "XT",
          "B2F",
          "Saigon Phantom",
          "V Gaming",
          "Box Gaming",
          "TDT Esports",
          "Team Flash",
          "Heavy",
          "MDH Esports",
          "HQ Esports",
          "Extreme Gaming",
          "B2F Gaming",
          "Thể Thao Điện Tử",
          "Esports",
          "Sport",
          "Sports"
        ],
        "categoryId": "20",
        "liveBroadcastContent": "none",
        "defaultLanguage": "vi",
        "localized": {
          "title": "HEV VS XT I SGP VS HQ I FL VS BOX I ĐẤU TRƯỜNG DANH VỌNG MÙA XUÂN 2023- VÒNG BẢNG NGÀY 19/02",
          "description": "HEV VS XT I SGP VS HQ I FL VS BOX I ĐẤU TRƯỜNG DANH VỌNG MÙA XUÂN 2023- VÒNG BẢNG NGÀY 19/02\n\nLịch thi đấu Đấu Trường Danh Vọng Mùa Xuân 2023 - Vòng Bảng ngày 19/02:\n\n👉 14h30: Heavy vs Extreme Gaming\n👉 17h00: Saigon Phantom vs HQ Esports\n👉 19h30: Team Flash vs Box Gaming\n\n🔥 Đấu Trường Danh Vọng chính thức khởi tranh mùa giải mới, 10 cái tên dù quen thuộc nhưng đội hình đã có phần thay đổi, liệu những cái tên nào sẽ giành lấy ưu thế trong chặng đầu tiên? \n\nĐừng bỏ lỡ những trận đấu hấp dẫn nhất của Đấu Trường Danh Vọng mùa Xuân 2023 diễn ra từ 16/2/2023 - 14/5/2023!!! \n#DauTruongDanhVong #TheThaoChoTheHeMoi #AOGS23 #8 #Haochuacay #Haogiaotranh #THtrueJUICEmilk #TiepNangLuongThienNhien\n\n00:00 Nhạc Chill Liên Quân\n04:35 Trước Giờ Cuồng Nhiệt\n36:48 HEV vs XT\n42:53 Ban Pick Game 1\n50:32 HEV vs XT - Game 1\n01:07:57 Ban Pick Game 2\n01:14:33 HEV vs XT - Game 2\n01:38:48 Ban Pick Game 3\n01:49:11 HEV vs XT - Game 3\n02:16:38 Phỏng Vấn Sau Trận - HEV Judas\n03:06:26 SGP vs HQ\n03:11:58 Ban Pick Game 1\n03:19:55 SGP vs HQ - Game 1\n03:43:41 Ban Pick Game 2\n03:50:58 SGP vs HQ - Game 2\n04:20:38 Ban Pick Game 3\n04:28:04 SGP vs HQ - Game 3\n04:49:03 Phỏng Vấn Sau Trận - SGP Red\n05:37:00 FL vs BOX\n05:45:03 Ban Pick Game 1\n05:54:14 FL vs BOX - Game 1\n06:23:43 Ban Pick Game 2\n06:29:13 FL vs BOX - Game 2\n07:08:00 Ban Pick Game 3\n07:15:51 FL vs BOX - Game 3\n07:37:58 Ban Pick Game 4\n07:45:52 FL vs BOX - Game 4\n08:06:22 Phỏng Vấn Sau Trận - FL 20Percent\n\n•••••••••••••••••••••••••••••••\nGarena Liên Quân Mobile & Liên Quân Mobile eSports - Garena  là 2 kênh YouTube chính thức của hội đồng Liên Quân, đồng thời là đơn vị duy nhất giữ bản quyền sản xuất toàn bộ nội dung game và giải đấu Liên Quân Mobile (Arena of Valor)."
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT8H12M",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": false,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "2231613",
        "likeCount": "15103",
        "favoriteCount": "0",
        "commentCount": "235"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "ClUoxLoZxFQAlfu6R2vJ4FUhamo",
      "id": "sjbdoIJ1nqI",
      "snippet": {
        "publishedAt": "2023-02-15T09:00:28Z",
        "channelId": "UCuNfWyYw1Ws9yzev3k7N3Kg",
        "title": "Gia đình bất ổn... || Nhã Bé Bắp",
        "description": "Gia đình bất ổn... || Nhã Bé Bắp\n\nNếu anh em thấy thú vị thì đừng quên để lại 1 like, subcribe & share để bắp🌽 có thêm động lực làm clip tiếp nhaaaa😋!\n________________\n\n✨Liên hệ Bắp tại:\n\n👉Instagram:  https://www.instagram.com/nhabebap/\n👉Tiktok: https://www.tiktok.com/@nhabebap?_t=8ZO8brjHSdt&_r=1\n👉Fanpage: https://www.facebook.com/nhabebap\n👉Facebook: https://www.facebook.com/tranvanthanh.nha\n\n________________\n\n© Mọi vấn đề về bản quyền vui lòng liên hệ:\nnhabebap2001@gmail.com\nMình sẽ giải quyết nhanh chóng và thiện chí nhất!\n©For any image and sound copyright issues, please contact us: nhabebap2001@gmail.com\n\n\n#nhabebap #nhabebaptiktok #shorts \n#funny #meme #bựa #kinhdi #vuive #haihuoc #hottiktok #tiktoktrend",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/sjbdoIJ1nqI/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/sjbdoIJ1nqI/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/sjbdoIJ1nqI/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/sjbdoIJ1nqI/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/sjbdoIJ1nqI/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Nhã Bé Bắp",
        "categoryId": "24",
        "liveBroadcastContent": "none",
        "localized": {
          "title": "Gia đình bất ổn... || Nhã Bé Bắp",
          "description": "Gia đình bất ổn... || Nhã Bé Bắp\n\nNếu anh em thấy thú vị thì đừng quên để lại 1 like, subcribe & share để bắp🌽 có thêm động lực làm clip tiếp nhaaaa😋!\n________________\n\n✨Liên hệ Bắp tại:\n\n👉Instagram:  https://www.instagram.com/nhabebap/\n👉Tiktok: https://www.tiktok.com/@nhabebap?_t=8ZO8brjHSdt&_r=1\n👉Fanpage: https://www.facebook.com/nhabebap\n👉Facebook: https://www.facebook.com/tranvanthanh.nha\n\n________________\n\n© Mọi vấn đề về bản quyền vui lòng liên hệ:\nnhabebap2001@gmail.com\nMình sẽ giải quyết nhanh chóng và thiện chí nhất!\n©For any image and sound copyright issues, please contact us: nhabebap2001@gmail.com\n\n\n#nhabebap #nhabebaptiktok #shorts \n#funny #meme #bựa #kinhdi #vuive #haihuoc #hottiktok #tiktoktrend"
        }
      },
      "contentDetails": {
        "duration": "PT58S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "1801243",
        "likeCount": "72105",
        "favoriteCount": "0",
        "commentCount": "288"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "3SCl0AlCR6qE5_XNdwftXkslCn0",
      "id": "zIcJJwplO0k",
      "snippet": {
        "publishedAt": "2023-02-12T09:00:27Z",
        "channelId": "UCF2UCA9SjmG-EWWELMQ8j8g",
        "title": "Linh Trang Xấu Xí - Luật Hoa quả Không Chừa Một Ai  #shorts by Hạnh Hạnh TV",
        "description": "Linh Trang Xấu Xí - Luật Hoa quả Không Chừa Một Ai  #shorts by Hạnh Hạnh TV\n\n#behanh#truyencotichdoithuc #hanhhanhtv #tiktok #giadinh",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/zIcJJwplO0k/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/zIcJJwplO0k/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/zIcJJwplO0k/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/zIcJJwplO0k/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/zIcJJwplO0k/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Hạnh Hạnh TV",
        "tags": [
          "linh trang xấu xí",
          "luật hoa quả không chừa một ai",
          "bắt nạt",
          "cha giàu vs cha nghèo",
          "giàu",
          "nghèo",
          "giàu vs nghèo",
          "cha giàu",
          "cha nghèo",
          "heartless father",
          "bố ơi",
          "hạnh hạnh tv",
          "bé hạnh",
          "truyện cổ tích đời thực",
          "phim gia đình cảm động",
          "bé hạnh xấu tính"
        ],
        "categoryId": "22",
        "liveBroadcastContent": "none",
        "localized": {
          "title": "Linh Trang Xấu Xí - Luật Hoa quả Không Chừa Một Ai  #shorts by Hạnh Hạnh TV",
          "description": "Linh Trang Xấu Xí - Luật Hoa quả Không Chừa Một Ai  #shorts by Hạnh Hạnh TV\n\n#behanh#truyencotichdoithuc #hanhhanhtv #tiktok #giadinh"
        },
        "defaultAudioLanguage": "en"
      },
      "contentDetails": {
        "duration": "PT59S",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "1919677",
        "likeCount": "35162",
        "favoriteCount": "0",
        "commentCount": "18"
      }
    },
    {
      "kind": "youtube#video",
      "etag": "lzZ-Td5Q9G7oToPqGap4CtjB3tA",
      "id": "6QpPOpO4KBo",
      "snippet": {
        "publishedAt": "2023-02-14T04:35:34Z",
        "channelId": "UCgsNtwJTZ8q-BXM1yTcGphQ",
        "title": "Đang hừng hực thì vợ lại đồi phí tình | #shorts",
        "description": "Đang hừng hực thì vợ lại đồi phí tình | #shorts \n\nĐăng ký kênh để theo dõi video mới nhất:\n👉https://bit.ly/LuatNhanQuaTV\n\nFanpage:\n👉 https://www.facebook.com/cucaitv.moli\n\nTiktok:\n👉 https://www.tiktok.com/@cucaitv.phimhay\n\nMọi ý kiến đóng góp và liên hệ:\nEmail: giaitri.minhtuan@gmail.com\n\nCảm ơn các bạn đã quan tâm và dành những trái tim yêu thương ủng hộ chúng tôi làm nên những tập phim ý nghĩa này!!!\n\n#LuatNhanQuaTV #NghiepQuat #Nhanqua",
        "thumbnails": {
          "default": {
            "url": "https://i.ytimg.com/vi/6QpPOpO4KBo/default.jpg",
            "width": 120,
            "height": 90
          },
          "medium": {
            "url": "https://i.ytimg.com/vi/6QpPOpO4KBo/mqdefault.jpg",
            "width": 320,
            "height": 180
          },
          "high": {
            "url": "https://i.ytimg.com/vi/6QpPOpO4KBo/hqdefault.jpg",
            "width": 480,
            "height": 360
          },
          "standard": {
            "url": "https://i.ytimg.com/vi/6QpPOpO4KBo/sddefault.jpg",
            "width": 640,
            "height": 480
          },
          "maxres": {
            "url": "https://i.ytimg.com/vi/6QpPOpO4KBo/maxresdefault.jpg",
            "width": 1280,
            "height": 720
          }
        },
        "channelTitle": "Luật Nhân Quả TV",
        "tags": [
          "Luật Nhân Quả",
          "luat nhan qua tv",
          "nghiệp quật",
          "Nghiep quat",
          "luat nhan qua",
          "nhung dua con bat hieu",
          "Phá án",
          "pha an",
          "Luật Nhân quả tv",
          "LuatNhanQuaTV",
          "NhanQuaTV",
          "Nhan qua TV",
          "Soái ca",
          "Thám tử",
          "luật nhân quả tv",
          "phim ngắn luật nhân quả tv",
          "phim ngắn luật nhân quả",
          "phim luật nhân quả",
          "phim luật nhân quả tv",
          "phim ngắn cảm động"
        ],
        "categoryId": "22",
        "liveBroadcastContent": "none",
        "defaultLanguage": "vi",
        "localized": {
          "title": "Đang hừng hực thì vợ lại đồi phí tình | #shorts",
          "description": "Đang hừng hực thì vợ lại đồi phí tình | #shorts \n\nĐăng ký kênh để theo dõi video mới nhất:\n👉https://bit.ly/LuatNhanQuaTV\n\nFanpage:\n👉 https://www.facebook.com/cucaitv.moli\n\nTiktok:\n👉 https://www.tiktok.com/@cucaitv.phimhay\n\nMọi ý kiến đóng góp và liên hệ:\nEmail: giaitri.minhtuan@gmail.com\n\nCảm ơn các bạn đã quan tâm và dành những trái tim yêu thương ủng hộ chúng tôi làm nên những tập phim ý nghĩa này!!!\n\n#LuatNhanQuaTV #NghiepQuat #Nhanqua"
        },
        "defaultAudioLanguage": "vi"
      },
      "contentDetails": {
        "duration": "PT1M",
        "dimension": "2d",
        "definition": "hd",
        "caption": "false",
        "licensedContent": true,
        "contentRating": {},
        "projection": "rectangular"
      },
      "statistics": {
        "viewCount": "1605276",
        "likeCount": "7834",
        "favoriteCount": "0",
        "commentCount": "189"
      }
    }
  ],
  "nextPageToken": "CAoQAA",
  "pageInfo": {
    "totalResults": 163,
    "resultsPerPage": 10
  }
}
""".trimIndent()

private val channelListJson = """
        {
          "kind": "youtube#channelListResponse",
          "etag": "BunmtKmv5Z5-WOUu80REF9SxQ6I",
          "pageInfo": {
            "totalResults": 10,
            "resultsPerPage": 10
          },
          "items": [
            {
              "kind": "youtube#channel",
              "etag": "kEonUp4T1A3t50fqlyclX1cFtoE",
              "id": "UCgsNtwJTZ8q-BXM1yTcGphQ",
              "snippet": {
                "title": "Luật Nhân Quả TV",
                "description": "Luật Nhân Quả TV\nTạo ra nhiều tập phim ý nghĩa sâu sắc, hấp dẫn,...Lên án những hành vi bất hiếu với Ông bà Cha mẹ, Những cạm bẫy xã hội, những hành vi lừa đảo nhầm trục lợi bản thân, những lối sống ích kỷ,...Được khắc hoạ một cách chân thật trong từng tập phim.\nNhầm mang những thông điệp giáo dục sâu sắc và thật tế trong từng tập phim gửi đến Quý khán giả!\n\nHãy theo dõi  chúng tôi để xem những tập phim ý nghãi mới nhất nha các bạn!\n👉https://bit.ly/LuatNhanQuaTV\nFanpage: 👉 https://www.facebook.com/Luatnhanquatv\n👉 Mọi ý kiến đóng góp và liên hệ Email: giaitri.minhtuan@gmail.com\nCảm ơn các bạn đã quan tâm và dành những trái tim yêu thương ủng hộ chúng tôi làm nên những tập phim ý nghĩa này!!!\n",
                "customUrl": "@luatnhanquatv",
                "publishedAt": "2017-06-01T03:42:19Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJXMBYK3GVfGTpAOXbwwSZt_vL7aFMBlLIJi96zbxA=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJXMBYK3GVfGTpAOXbwwSZt_vL7aFMBlLIJi96zbxA=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJXMBYK3GVfGTpAOXbwwSZt_vL7aFMBlLIJi96zbxA=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "defaultLanguage": "vi",
                "localized": {
                  "title": "Luật Nhân Quả TV",
                  "description": "Luật Nhân Quả TV\nTạo ra nhiều tập phim ý nghĩa sâu sắc, hấp dẫn,...Lên án những hành vi bất hiếu với Ông bà Cha mẹ, Những cạm bẫy xã hội, những hành vi lừa đảo nhầm trục lợi bản thân, những lối sống ích kỷ,...Được khắc hoạ một cách chân thật trong từng tập phim.\nNhầm mang những thông điệp giáo dục sâu sắc và thật tế trong từng tập phim gửi đến Quý khán giả!\n\nHãy theo dõi  chúng tôi để xem những tập phim ý nghãi mới nhất nha các bạn!\n👉https://bit.ly/LuatNhanQuaTV\nFanpage: 👉 https://www.facebook.com/Luatnhanquatv\n👉 Mọi ý kiến đóng góp và liên hệ Email: giaitri.minhtuan@gmail.com\nCảm ơn các bạn đã quan tâm và dành những trái tim yêu thương ủng hộ chúng tôi làm nên những tập phim ý nghĩa này!!!\n"
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "84527686",
                "subscriberCount": "143000",
                "hiddenSubscriberCount": false,
                "videoCount": "665"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02jjt",
                  "/m/0f2f9",
                  "/m/02vxn"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Entertainment",
                  "https://en.wikipedia.org/wiki/Television_program",
                  "https://en.wikipedia.org/wiki/Film"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "wWGnNPwRuSqMt5CwNdNEWGVs1gc",
              "id": "UCSGiYwWpkUUAgqfbFj9J46A",
              "snippet": {
                "title": "Little Golden",
                "description": "Oh everyone left 🥺\nExcept u!❤️\nWant to play with me?\nDaily updates Subscribe now for love!\n",
                "customUrl": "@littlegolden33",
                "publishedAt": "2022-11-12T10:39:47.57982Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/Rolnwtso3DeRLyXHnUmawnik4NCUgJNJTnAG7VsweyOgOEp2fGI_RocPaHUNA7tUXbLxzH8LoFs=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/Rolnwtso3DeRLyXHnUmawnik4NCUgJNJTnAG7VsweyOgOEp2fGI_RocPaHUNA7tUXbLxzH8LoFs=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/Rolnwtso3DeRLyXHnUmawnik4NCUgJNJTnAG7VsweyOgOEp2fGI_RocPaHUNA7tUXbLxzH8LoFs=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Little Golden",
                  "description": "Oh everyone left 🥺\nExcept u!❤️\nWant to play with me?\nDaily updates Subscribe now for love!\n"
                },
                "country": "US"
              },
              "statistics": {
                "viewCount": "131036259",
                "subscriberCount": "502000",
                "hiddenSubscriberCount": false,
                "videoCount": "241"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02jjt",
                  "/m/068hy",
                  "/m/019_rr"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Entertainment",
                  "https://en.wikipedia.org/wiki/Pet",
                  "https://en.wikipedia.org/wiki/Lifestyle_(sociology)"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "F_JOAISzn_oXhIgfOJPDjrR9mXc",
              "id": "UCVIIa6OL-FautUqhHjAoz_A",
              "snippet": {
                "title": "ĐỨC PHÚC OFFICIAL",
                "description": "Đây là kênh chính thức của ca sĩ Đức Phúc. Các sản phẩm âm nhạc của Phúc sẽ được upload tại đây. Các bạn subscribe kênh của Phúc để tiện theo dõi nhé.",
                "customUrl": "@ducphucofficial",
                "publishedAt": "2015-09-24T02:32:18Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/uNtMsEqpSKHEdN7PXmGL5BO1FlYXtv10Rln9XO7bEwKkH86Jd4djv6s5JeWT9GYbpnLZU0AB=s88-c-k-c0x00ffffff-no-nd-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/uNtMsEqpSKHEdN7PXmGL5BO1FlYXtv10Rln9XO7bEwKkH86Jd4djv6s5JeWT9GYbpnLZU0AB=s240-c-k-c0x00ffffff-no-nd-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/uNtMsEqpSKHEdN7PXmGL5BO1FlYXtv10Rln9XO7bEwKkH86Jd4djv6s5JeWT9GYbpnLZU0AB=s800-c-k-c0x00ffffff-no-nd-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "ĐỨC PHÚC OFFICIAL",
                  "description": "Đây là kênh chính thức của ca sĩ Đức Phúc. Các sản phẩm âm nhạc của Phúc sẽ được upload tại đây. Các bạn subscribe kênh của Phúc để tiện theo dõi nhé."
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "779878617",
                "subscriberCount": "1830000",
                "hiddenSubscriberCount": false,
                "videoCount": "144"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/04rlf",
                  "/m/064t9",
                  "/m/028sqc"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Music",
                  "https://en.wikipedia.org/wiki/Pop_music",
                  "https://en.wikipedia.org/wiki/Music_of_Asia"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "RN6qlejTXK3zoKfId6rJ8tM3An0",
              "id": "UCFMEYTv6N64hIL9FlQ_hxBw",
              "snippet": {
                "title": "ĐÔNG TÂY PROMOTION OFFICIAL",
                "description": "Đông Tây Promotion là công ty sản xuất các chương trình truyền hình, quản lý dự án và tổ chức sự kiện\n\nCông ty Đông Tây Promotion đặt trọng tâm vào các dịch vụ chính yếu bao gồm sản xuất các chương trình truyền hình, quản lý dự án và tổ chức sự kiện với các thể loại như: Trò chơi truyền hình, truyền hình thực tế, các chương trình giải trí Âm nhạc, các sự kiện thể thao, các chương trình tạp kỹ…\n\n► Fanpage: https://www.facebook.com/DongTayPromotion\n",
                "customUrl": "@dongtaypromotionofficial",
                "publishedAt": "2014-12-17T03:07:44Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJX-eS0eDbMS4uhqmV71wp6qdM_y9FUYFHwJQKn16w=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJX-eS0eDbMS4uhqmV71wp6qdM_y9FUYFHwJQKn16w=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/ytc/AL5GRJX-eS0eDbMS4uhqmV71wp6qdM_y9FUYFHwJQKn16w=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "ĐÔNG TÂY PROMOTION OFFICIAL",
                  "description": "Đông Tây Promotion là công ty sản xuất các chương trình truyền hình, quản lý dự án và tổ chức sự kiện\n\nCông ty Đông Tây Promotion đặt trọng tâm vào các dịch vụ chính yếu bao gồm sản xuất các chương trình truyền hình, quản lý dự án và tổ chức sự kiện với các thể loại như: Trò chơi truyền hình, truyền hình thực tế, các chương trình giải trí Âm nhạc, các sự kiện thể thao, các chương trình tạp kỹ…\n\n► Fanpage: https://www.facebook.com/DongTayPromotion\n"
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "5445180711",
                "subscriberCount": "8560000",
                "hiddenSubscriberCount": false,
                "videoCount": "8077"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/0f2f9",
                  "/m/02jjt",
                  "/m/02vxn"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Television_program",
                  "https://en.wikipedia.org/wiki/Entertainment",
                  "https://en.wikipedia.org/wiki/Film"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "gkE3rb3npQCCSa4XFdt1DbMTTYQ",
              "id": "UCmkXtw92W4G5NZMRr72otiw",
              "snippet": {
                "title": "Chia Sẻ Kiến Thức Implant",
                "description": "Kênh video chính thức của nha khoa Lạc Việt Intech  Implant, nơi các bạn có thể tham khảo các video để hiểu rõ hơn về quá trình trồng răng implant, các thông tin liên quan đến nha khoa được chia sẻ dưới góc nhìn của chính những bác sĩ đang công tác tại Lạc Việt Intech. Với đội ngũ bác sĩ giàu kinh nghiệm sẽ giải đáp tất cả các thắc mắc của bạn về trồng răng. Cùng xem những video hay ho và cực bổ ích tại đây nhé!\n\n#nhakhoa #nhakhoalacviet #implant #niengrang #LVnetwork\n- Đăng Ký     :  bit.ly/3tM1hzg\n- Facebook  :  bit.ly/3OwTuOM\n📳Tổng đài CSKH  : 19006421\n☎ Hotline    : 0971066726 - 096.192.0606\n💻 Website : https://lacvietintech.com/\n🏠 Địa chỉ:\nHệ thống Nha khoa Lạc Việt Intech:\n– Trụ sở Vinh: Số 22 Cao Thắng, P. Hồng Sơn, TP Vinh, Nghệ An\n– Trụ sở Hải Phòng: 107 Tô Hiệu, Lê Chân, TP Hải Phòng\n– Trụ sở Quảng Ninh: Tp Quảng Ninh (Coming soon)\n",
                "customUrl": "@chiasekienthucimplant",
                "publishedAt": "2022-06-14T09:25:38.988633Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/RcEe0YF5k6YCy6gatJDh4wszMxSvy8K6XJjQ5bDHIS8hlhOXAeiF7NFJt8tjzCCE95Ss0eX99A=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/RcEe0YF5k6YCy6gatJDh4wszMxSvy8K6XJjQ5bDHIS8hlhOXAeiF7NFJt8tjzCCE95Ss0eX99A=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/RcEe0YF5k6YCy6gatJDh4wszMxSvy8K6XJjQ5bDHIS8hlhOXAeiF7NFJt8tjzCCE95Ss0eX99A=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Chia Sẻ Kiến Thức Implant",
                  "description": "Kênh video chính thức của nha khoa Lạc Việt Intech  Implant, nơi các bạn có thể tham khảo các video để hiểu rõ hơn về quá trình trồng răng implant, các thông tin liên quan đến nha khoa được chia sẻ dưới góc nhìn của chính những bác sĩ đang công tác tại Lạc Việt Intech. Với đội ngũ bác sĩ giàu kinh nghiệm sẽ giải đáp tất cả các thắc mắc của bạn về trồng răng. Cùng xem những video hay ho và cực bổ ích tại đây nhé!\n\n#nhakhoa #nhakhoalacviet #implant #niengrang #LVnetwork\n- Đăng Ký     :  bit.ly/3tM1hzg\n- Facebook  :  bit.ly/3OwTuOM\n📳Tổng đài CSKH  : 19006421\n☎ Hotline    : 0971066726 - 096.192.0606\n💻 Website : https://lacvietintech.com/\n🏠 Địa chỉ:\nHệ thống Nha khoa Lạc Việt Intech:\n– Trụ sở Vinh: Số 22 Cao Thắng, P. Hồng Sơn, TP Vinh, Nghệ An\n– Trụ sở Hải Phòng: 107 Tô Hiệu, Lê Chân, TP Hải Phòng\n– Trụ sở Quảng Ninh: Tp Quảng Ninh (Coming soon)\n"
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "283415564",
                "subscriberCount": "245000",
                "hiddenSubscriberCount": false,
                "videoCount": "149"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/01k8wb",
                  "/m/019_rr",
                  "/m/0kt51",
                  "/m/02jjt"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Knowledge",
                  "https://en.wikipedia.org/wiki/Lifestyle_(sociology)",
                  "https://en.wikipedia.org/wiki/Health",
                  "https://en.wikipedia.org/wiki/Entertainment"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "-CWsLJ4WTeQhWjR2kn08ez94Z6M",
              "id": "UC4LvrpNXujjbGOS4RDvr41g",
              "snippet": {
                "title": "FPT Bóng Đá",
                "description": "FPT Play là đơn vị sở hữu toàn bộ bản quyền các giải đấu cấp CLB của UEFA trong 3 mùa giải từ 2021-2024. \n\nFPT Play - Không giới hạn\n------------------------------\nThưởng thức các trận cầu đỉnh cao tại hệ thống kênh Youtube của FPT\n️⚽ FPT Bóng Đá: https://bit.ly/FPTBongda\n⚽ FPT Bóng Đá Việt: https://bit.ly/FPTBongDaViet\n⚽ FPT Bóng Rổ: https://bit.ly/FPTBongro\n------------------------------\n© Bản quyền thuộc về FPT Bóng Đá và Đối Tác\n© Copyright by FPT Bóng Đá and Partner☞ Do not Reup\n© Copyright all rights reserved\n",
                "customUrl": "@fbongda",
                "publishedAt": "2017-12-18T03:31:54Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/_ZlG6PR6zyfOCaSL1KFHmIOZWR3tDHqLj_21IBf4LqryN9QJuECX_7WYsBSvOVhjxIVXHYj0=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/_ZlG6PR6zyfOCaSL1KFHmIOZWR3tDHqLj_21IBf4LqryN9QJuECX_7WYsBSvOVhjxIVXHYj0=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/_ZlG6PR6zyfOCaSL1KFHmIOZWR3tDHqLj_21IBf4LqryN9QJuECX_7WYsBSvOVhjxIVXHYj0=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "defaultLanguage": "vi",
                "localized": {
                  "title": "FPT Bóng Đá",
                  "description": "FPT Play là đơn vị sở hữu toàn bộ bản quyền các giải đấu cấp CLB của UEFA trong 3 mùa giải từ 2021-2024. \n\nFPT Play - Không giới hạn\n------------------------------\nThưởng thức các trận cầu đỉnh cao tại hệ thống kênh Youtube của FPT\n️⚽ FPT Bóng Đá: https://bit.ly/FPTBongda\n⚽ FPT Bóng Đá Việt: https://bit.ly/FPTBongDaViet\n⚽ FPT Bóng Rổ: https://bit.ly/FPTBongro\n------------------------------\n© Bản quyền thuộc về FPT Bóng Đá và Đối Tác\n© Copyright by FPT Bóng Đá and Partner☞ Do not Reup\n© Copyright all rights reserved\n"
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "507647070",
                "subscriberCount": "959000",
                "hiddenSubscriberCount": false,
                "videoCount": "2551"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02vx4",
                  "/m/06ntj"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Association_football",
                  "https://en.wikipedia.org/wiki/Sport"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "E2NBLBI-hAHloh5APREXY95ODCY",
              "id": "UCuNfWyYw1Ws9yzev3k7N3Kg",
              "snippet": {
                "title": "Nhã Bé Bắp",
                "description": "Channel này sẽ là một góc điện ảnh nhỏ 🎞 những chiếc video hài hước, giải trí \"chấn động bờ hồ\" của hot Tiktoker ✨Nhã Bé Bắp✨\n\nNếu anh em thấy thú vị thì đừng quên để lại 1 like, subcribe & share để bắp có thêm động lực làm clip nhé!\n________________\n\n© Mọi vấn đề về bản quyền vui lòng liên hệ:\nnhabebap2001@gmail.com\nMình sẽ giải quyết nhanh chóng và thiện chí nhất!\n©For any image and sound copyright issues, please contact us nhabebap2001@gmail.com\n\n\n",
                "customUrl": "@nhabebap",
                "publishedAt": "2013-06-14T09:24:27Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/IggZ_7Uq85oUlkJRWvfCYk8TXn2o7XLv3AAFgA0NhKJ1IT5NhbaCN4R9AQiIPuyBlYrx8uvG=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/IggZ_7Uq85oUlkJRWvfCYk8TXn2o7XLv3AAFgA0NhKJ1IT5NhbaCN4R9AQiIPuyBlYrx8uvG=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/IggZ_7Uq85oUlkJRWvfCYk8TXn2o7XLv3AAFgA0NhKJ1IT5NhbaCN4R9AQiIPuyBlYrx8uvG=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Nhã Bé Bắp",
                  "description": "Channel này sẽ là một góc điện ảnh nhỏ 🎞 những chiếc video hài hước, giải trí \"chấn động bờ hồ\" của hot Tiktoker ✨Nhã Bé Bắp✨\n\nNếu anh em thấy thú vị thì đừng quên để lại 1 like, subcribe & share để bắp có thêm động lực làm clip nhé!\n________________\n\n© Mọi vấn đề về bản quyền vui lòng liên hệ:\nnhabebap2001@gmail.com\nMình sẽ giải quyết nhanh chóng và thiện chí nhất!\n©For any image and sound copyright issues, please contact us nhabebap2001@gmail.com\n\n\n"
                }
              },
              "statistics": {
                "viewCount": "895748214",
                "subscriberCount": "1270000",
                "hiddenSubscriberCount": false,
                "videoCount": "283"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02jjt",
                  "/m/019_rr"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Entertainment",
                  "https://en.wikipedia.org/wiki/Lifestyle_(sociology)"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "9kdDNN2Bt52PraDeVO1Khds6Gow",
              "id": "UCpnQwjzvDm1MOMtZV2zkVpA",
              "snippet": {
                "title": "Liên Quân Mobile eSports-Garena",
                "description": "",
                "customUrl": "@lienquanmobileesportsgarena",
                "publishedAt": "2016-10-18T08:21:21Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/Q1lJmx9A_riu_8YTk7WvsEtEisPTPhP9dM4YdQ_URH-I7QFG1UhfKIvjMxSMkamq0jOzcedk0OA=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/Q1lJmx9A_riu_8YTk7WvsEtEisPTPhP9dM4YdQ_URH-I7QFG1UhfKIvjMxSMkamq0jOzcedk0OA=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/Q1lJmx9A_riu_8YTk7WvsEtEisPTPhP9dM4YdQ_URH-I7QFG1UhfKIvjMxSMkamq0jOzcedk0OA=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Liên Quân Mobile eSports-Garena",
                  "description": ""
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "2178309046",
                "subscriberCount": "4750000",
                "hiddenSubscriberCount": false,
                "videoCount": "5867"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/0bzvm2",
                  "/m/03hf_rm",
                  "/m/025zzc"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Video_game_culture",
                  "https://en.wikipedia.org/wiki/Strategy_video_game",
                  "https://en.wikipedia.org/wiki/Action_game"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "rpVmJWw8a8BuDwIWvhCLpY26l5E",
              "id": "UCF2UCA9SjmG-EWWELMQ8j8g",
              "snippet": {
                "title": "Hạnh Hạnh TV",
                "description": "",
                "customUrl": "@hanhhanhtv2011",
                "publishedAt": "2022-03-16T07:26:54.715192Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/drZTM28yvv46pUZaZ1kfktviQ1Za0EbqzlhIlHgZmtmNd_xZSQqvtLAYsGxUfG5KGIgpIyev=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/drZTM28yvv46pUZaZ1kfktviQ1Za0EbqzlhIlHgZmtmNd_xZSQqvtLAYsGxUfG5KGIgpIyev=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/drZTM28yvv46pUZaZ1kfktviQ1Za0EbqzlhIlHgZmtmNd_xZSQqvtLAYsGxUfG5KGIgpIyev=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Hạnh Hạnh TV",
                  "description": ""
                }
              },
              "statistics": {
                "viewCount": "17889983",
                "subscriberCount": "22100",
                "hiddenSubscriberCount": false,
                "videoCount": "40"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02jjt",
                  "/m/02vxn"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Entertainment",
                  "https://en.wikipedia.org/wiki/Film"
                ]
              }
            },
            {
              "kind": "youtube#channel",
              "etag": "KtybgQX2Ht47wCF2_ESn-ziiI7c",
              "id": "UCzSl8LJLpB3LmoY18oIWrtw",
              "snippet": {
                "title": "Bảo Ngân",
                "description": "Một người hay chế.\n\nTheo dõi Bảo Ngân trên:\nTiktok: https://www.tiktok.com/@ngan.549\nFacebook: https://www.facebook.com/quy.549\nEmail: thichche209@gmail.com\n",
                "customUrl": "@baongan.549",
                "publishedAt": "2021-06-26T09:38:49.821403Z",
                "thumbnails": {
                  "default": {
                    "url": "https://yt3.ggpht.com/z5vDiDaiJnxV5HXUsiNNiZrmWpO8wTzADOkOk8_mOGt2o8KieercVx_FE9L6PihXKNI2nJsNmg=s88-c-k-c0x00ffffff-no-rj",
                    "width": 88,
                    "height": 88
                  },
                  "medium": {
                    "url": "https://yt3.ggpht.com/z5vDiDaiJnxV5HXUsiNNiZrmWpO8wTzADOkOk8_mOGt2o8KieercVx_FE9L6PihXKNI2nJsNmg=s240-c-k-c0x00ffffff-no-rj",
                    "width": 240,
                    "height": 240
                  },
                  "high": {
                    "url": "https://yt3.ggpht.com/z5vDiDaiJnxV5HXUsiNNiZrmWpO8wTzADOkOk8_mOGt2o8KieercVx_FE9L6PihXKNI2nJsNmg=s800-c-k-c0x00ffffff-no-rj",
                    "width": 800,
                    "height": 800
                  }
                },
                "localized": {
                  "title": "Bảo Ngân",
                  "description": "Một người hay chế.\n\nTheo dõi Bảo Ngân trên:\nTiktok: https://www.tiktok.com/@ngan.549\nFacebook: https://www.facebook.com/quy.549\nEmail: thichche209@gmail.com\n"
                },
                "country": "VN"
              },
              "statistics": {
                "viewCount": "506407851",
                "subscriberCount": "699000",
                "hiddenSubscriberCount": false,
                "videoCount": "126"
              },
              "topicDetails": {
                "topicIds": [
                  "/m/02jjt"
                ],
                "topicCategories": [
                  "https://en.wikipedia.org/wiki/Entertainment"
                ]
              }
            }
          ]
        }
    """.trimIndent()