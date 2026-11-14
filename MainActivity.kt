package com.amjadcreates.studio

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val ids = listOf(
    "Amjad creates", "Amjad creates magic", "Amjad creates kids", "Amjad viral world"
)
private val themes = listOf(
    "বুশরা ইউসুফের খেলনা লুকিয়ে রাখে, পরে দুজন মিলে খুঁজতে গিয়ে হাসির ঘটনা ঘটে",
    "ইউসুফ রান্নাঘরে মাকে সাহায্য করতে গিয়ে মজার ভুল করে, হাবিবা আদর করে শেখান",
    "বুশরা ও ইউসুফ বাগানে দুষ্টুমি করতে গিয়ে একটি ছোট্ট পাখিকে সাহায্য করে",
    "আবু বকর সন্তানদের সঙ্গে বুদ্ধির খেলা খেলেন, শেষে সবাই হেসে ওঠে",
    "বুশরা ও ইউসুফ খেলনা ভাগাভাগি নিয়ে ঝগড়া করে, পরে ক্ষমা চেয়ে মিলে যায়",
    "পরিবার মিলে বৃষ্টিতে আটকে থাকা বিড়ালছানাকে নিরাপদে আশ্রয় দেয়",
    "ইউসুফ নামাজের আগে জায়নামাজ গুছিয়ে দিতে গিয়ে মিষ্টি কাণ্ড ঘটায়",
    "হাবিবা বুশরা ও ইউসুফকে সত্য কথা বলার সুন্দর শিক্ষা দেন",
    "আবু বকর সন্তানদের নিয়ে ফলের বাগানে যান, সেখানে মজার প্রতিযোগিতা হয়",
    "বুশরা ইউসুফকে চমকে দিতে চায়, কিন্তু নিজেরাই হাসির ফাঁদে পড়ে"
)
private val categoryTwists = listOf(
    "পরিবারের মজার দুষ্টুমি ও হাসাহাসি",
    "কল্পনাময় কিন্তু ইসলামিক মূল্যবোধসম্মত ছোট্ট অভিযান",
    "শিশুদের আদব, দয়া, সততা ও সহযোগিতার শিক্ষা",
    "বন্ধু ও পরিবারের সঙ্গে হাসির ঘটনা এবং ভালো কাজ"
)
private fun storyTitle(i: Int, category: Int) = when(category) {
    0 -> listOf("বুশরার গোপন কাণ্ড", "ইউসুফের মজার ভুল", "বাবার হাসির পরীক্ষা", "মায়ের রান্নাঘরের অভিযান")[i % 4] + " ${i+1}"
    1 -> listOf("জাদুর বাগানের রহস্য", "রঙিন ঘুড়ির অভিযান", "চাঁদের আলোয় মজার খেলা", "বন্ধুত্বের আশ্চর্য বাক্স")[i % 4] + " ${i+1}"
    2 -> listOf("সুন্দর আদবের পাঠ", "সত্য বলার সাহস", "ভাগাভাগির আনন্দ", "সালামের মিষ্টি অভ্যাস")[i % 4] + " ${i+1}"
    else -> listOf("হাসির ঝড়", "দুষ্টু বুশরার কাণ্ড", "ইউসুফের বুদ্ধির খেলা", "পরিবারের মজার দিন")[i % 4] + " ${i+1}"
}
private fun promptFor(story: String, category: Int): String =
    """১০ সেকেন্ডের 9:16 ভার্টিক্যাল 3D কার্টুন ভিডিও। প্রথম ৩ সেকেন্ডে আকর্ষণীয় মজার হুক। গল্প: $story। ${categoryTwists[category]}।
চরিত্রের পরিচয় ও ধারাবাহিকতা: বাবা আবু বকর, মা হাবিবা, মেয়ে বুশরা, ছেলে ইউসুফ। সব ভিডিওতে চারজনের মুখ, বয়স, চুল, উচ্চতা, গড়ন ও পোশাকের ডিজাইন একই রাখবে। শালীন ইসলামিক পোশাক: আবু বকরের সাদামাটা পাঞ্জাবি ও টুপি, হাবিবার পর্দাসম্মত হিজাব ও পোশাক, বুশরার শিশু-উপযোগী হিজাব ও লম্বা পোশাক, ইউসুফের শিশু-উপযোগী পাঞ্জাবি ও টুপি। উষ্ণ বাংলাদেশি পারিবারিক পরিবেশ, সুন্দর 3D অ্যানিমেশন, অভিব্যক্তিপূর্ণ মুখ, স্বাভাবিক ঠোঁটের নড়াচড়া, মিষ্টি বাংলা শিশুকণ্ঠ, হাসি ও প্রাণবন্ত অ্যাকশন। কোনো ইংরেজি সংলাপ নয়। দৃশ্য ও সংলাপ ১০ সেকেন্ডের মধ্যে সম্পূর্ণ হবে। চরিত্রের চেহারা পরিবর্তন করবে না।"""
private fun hashtags(category: Int) = listOf(
    "#AmjadCreates #FamilyCartoon #FunnyKids #BanglaAnimation",
    "#AmjadCreatesMagic #KidsAdventure #BanglaCartoon #FamilyFun",
    "#AmjadCreatesKids #IslamicKids #শিশুদেরআদব #BanglaCartoon",
    "#AmjadViralWorld #FunnyCartoon #BanglaKids #FamilyComedy"
)[category]

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudioApp() }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioApp() {
    val context = LocalContext.current
    var category by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(0) }
    var customStory by remember { mutableStateOf("") }
    var useCustom by remember { mutableStateOf(false) }
    val title = if (useCustom) "আপনার নিজের গল্প" else storyTitle(selected, category)
    val story = if (useCustom) customStory else themes[(selected + category * 3) % themes.size]
    val prompt = remember(story, category) { promptFor(story, category) }
    val titleTags = "$title\n${hashtags(category)}"
    fun copy(text: String, message: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Amjad Creates Studio", text))
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    MaterialTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("Amjad Creates Studio") }) }) { padding ->
            Column(Modifier.padding(padding).padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("আপনার চারটি আইডির গল্পের স্টুডিও", style = MaterialTheme.typography.titleMedium)
                ids.forEachIndexed { index, name ->
                    OutlinedButton(onClick = { category = index; selected = 0; useCustom = false }, modifier = Modifier.fillMaxWidth()) {
                        Text((if (category == index) "✓ " else "") + name + " — ১০০টি গল্প")
                    }
                }
                HorizontalDivider()
                Text("গল্প নির্বাচন", style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { useCustom = false }) { Text("তৈরি গল্প") }
                    OutlinedButton(onClick = { useCustom = true }) { Text("নিজের গল্প") }
                }
                if (!useCustom) {
                    Text("গল্প ${selected + 1} / ১০০")
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = { selected = (selected + 99) % 100 }) { Text("আগের") }
                        OutlinedButton(onClick = { selected = (selected + 1) % 100 }) { Text("পরের") }
                    }
                    Text(title, style = MaterialTheme.typography.titleLarge)
                    Text(story)
                } else {
                    OutlinedTextField(value = customStory, onValueChange = { customStory = it }, label = { Text("আপনার গল্প লিখুন") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
                }
                Text("Google Flow ভিডিও প্রম্পট", style = MaterialTheme.typography.titleMedium)
                Text(prompt)
                Button(onClick = { copy(prompt, "ভিডিও প্রম্পট কপি হয়েছে") }, modifier = Modifier.fillMaxWidth()) { Text("ভিডিও প্রম্পট কপি করুন") }
                Text("টাইটেল ও ৪টি হ্যাশট্যাগ", style = MaterialTheme.typography.titleMedium)
                Text(titleTags)
                OutlinedButton(onClick = { copy(titleTags, "টাইটেল ও হ্যাশট্যাগ কপি হয়েছে") }, modifier = Modifier.fillMaxWidth()) { Text("টাইটেল + হ্যাশট্যাগ কপি") }
                Text("নিরাপত্তা: অ্যাপটি অফলাইন। কোনো লগইন, ট্র্যাকিং, বিজ্ঞাপন বা ইন্টারনেট পারমিশন নেই। Google Flow-তে ভিডিও তৈরির জন্য আলাদা ইন্টারনেট লাগবে।", style = MaterialTheme.typography.bodySmall)
                Text("নোট: ১০০টি এন্ট্রি নম্বরসহ গল্পের ধারণা হিসেবে তৈরি; প্রতিটি এন্ট্রির পূর্ণাঙ্গ আলাদা কাহিনি ও সংলাপ পরবর্তী কনটেন্ট আপডেটে যোগ হবে।", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
