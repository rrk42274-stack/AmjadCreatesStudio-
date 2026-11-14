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
"Amjad creates",
"Amjad creates magic",
"Amjad creates kids",
"Amjad viral world"
)

private val stories = listOf(
"বুশরা ইউসুফের খেলনা লুকিয়ে রাখে, পরে দুজন মিলে খুঁজতে যায়।",
"ইউসুফ রান্নাঘরে মাকে সাহায্য করতে গিয়ে মজার ভুল করে।",
"বুশরা ও ইউসুফ বাগানে একটি ছোট পাখিকে সাহায্য করে।",
"আবু বকর সন্তানদের সঙ্গে বুদ্ধির খেলা খেলেন।",
"বুশরা ও ইউসুফ খেলনা ভাগাভাগি করতে শেখে।",
"পরিবার মিলে একটি বিড়ালছানাকে আশ্রয় দেয়।",
"ইউসুফ নামাজের আগে জায়নামাজ গুছিয়ে রাখে।",
"হাবিবা সন্তানদের সত্য কথা বলার শিক্ষা দেন।",
"পরিবার মিলে ফলের বাগানে আনন্দ করে।",
"বুশরা ইউসুফকে চমকে দিতে গিয়ে নিজেই চমকে যায়।"
)

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

val story = if (useCustom) customStory else stories[selected % stories.size]

val prompt = """

১০ সেকেন্ডের 9:16 ভার্টিক্যাল 3D কার্টুন ভিডিও।
প্রথম ৩ সেকেন্ডে আকর্ষণীয় হুক থাকবে।
গল্প: $story

চরিত্র:
বাবা আবু বকর, মা হাবিবা,
মেয়ে বুশরা এবং ছেলে ইউসুফ।
প্রতিটি ভিডিওতে সবার চেহারা ও পোশাক একই থাকবে।
শালীন ইসলামিক পোশাক, সুন্দর বাংলাদেশি পরিবেশ,
উষ্ণ আলো, মিষ্টি বাংলা শিশুকণ্ঠ,
স্বাভাবিক ঠোঁটের নড়াচড়া ও আনন্দময় অভিনয়।
কোনো ইংরেজি সংলাপ নয়।
""".trimIndent()

val hashtags = listOf(
    "#AmjadCreates #FamilyCartoon #FunnyKids #BanglaAnimation",
    "#AmjadCreatesMagic #KidsAdventure #BanglaCartoon #FamilyFun",
    "#AmjadCreatesKids #IslamicKids #শিশুদেরআদব #BanglaCartoon",
    "#AmjadViralWorld #FunnyCartoon #BanglaKids #FamilyComedy"
)[category]

val title = "মজার গল্প ${selected + 1}\n$hashtags"

fun copyText(value: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("Amjad Creates", value))
    Toast.makeText(context, "কপি হয়েছে", Toast.LENGTH_SHORT).show()
}

MaterialTheme {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Amjad Creates Studio") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("আপনার চারটি আইডির গল্পের স্টুডিও")

            ids.forEachIndexed { index, name ->
                OutlinedButton(
                    onClick = {
                        category = index
                        selected = 0
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(name)
                }
            }

            HorizontalDivider()

            Text("গল্প নির্বাচন করুন")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { useCustom = false }) {
                    Text("তৈরি গল্প")
                }
                OutlinedButton(onClick = { useCustom = true }) {
                    Text("নিজের গল্প")
                }
            }

            if (!useCustom) {
                Text("গল্প ${selected + 1} / ১০০")
                Text(story)

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { selected = (selected + 99) % 100 }
                    ) { Text("আগের") }

                    Button(
                        onClick = { selected = (selected + 1) % 100 }
                    ) { Text("পরের") }
                }
            } else {
                OutlinedTextField(
                    value = customStory,
                    onValueChange = { customStory = it },
                    label = { Text("নিজের গল্প লিখুন") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }

            Text("ভিডিও তৈরির প্রম্পট", style = MaterialTheme.typography.titleMedium)
            Text(prompt)

            Button(
                onClick = { copyText(prompt) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ভিডিও প্রম্পট কপি করুন")
            }

            HorizontalDivider()

            Text("টাইটেল ও ৪টি হ্যাশট্যাগ")
            Text(title)

            OutlinedButton(
                onClick = { copyText(title) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("টাইটেল ও হ্যাশট্যাগ কপি করুন")
            }
        }
    }
}

}