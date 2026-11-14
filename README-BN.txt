Amjad Creates Studio — মোবাইল থেকে APK তৈরির প্যাকেজ

এই প্যাকেজে Android অ্যাপের সোর্স কোড ও GitHub Actions-এর ক্লাউড বিল্ড ওয়ার্কফ্লো আছে। এই চ্যাটে APK কম্পাইল করা হয়নি।

মোবাইল থেকে APK বানানোর সংক্ষিপ্ত ধাপ:
১) ZIP ফাইলটি Android ফোনে Extract করুন।
২) GitHub অ্যাকাউন্টে নতুন Public বা Private repository তৈরি করুন।
৩) Extract করা ফোল্ডারের ভেতরের ফাইলগুলো (app ফোল্ডার, .github ফোল্ডার, build.gradle.kts, settings.gradle.kts, gradle.properties) repository-তে আপলোড করুন। .github/workflows/build-apk.yml-ও আপলোড হতে হবে।
৪) Repository-র Actions ট্যাবে যান। "Build Android APK" workflow নির্বাচন করে Run workflow চাপুন।
৫) Build সফল হলে Actions-এর সর্বশেষ run খুলে Artifacts থেকে Amjad-Creates-Studio-APK ডাউনলোড করুন।
৬) ZIP artifact Extract করে app-debug.apk খুলে Android-এর অনুমতি দিয়ে ইনস্টল করুন।

নিরাপত্তা:
- অ্যাপে INTERNET, location, camera, microphone, contacts বা storage permission নেই।
- অ্যাপ অফলাইনে গল্প/প্রম্পট তৈরি ও কপি করে।
- APKটি debug-signed হবে; এটি নিজের ফোনে পরীক্ষা করার জন্য। কাউকে বিতরণ করতে হলে আলাদা release signing দরকার।
- কোনো অ্যাপ ১০০% হ্যাক-প্রুফ নয়। ফোন আপডেট রাখুন এবং অজানা APK ইনস্টল করবেন না।
- Clipboard-এ কপি করা লেখা কিবোর্ড/সিস্টেম clipboard history-তে দেখা যেতে পারে।

কনটেন্টের অবস্থা:
চারটি আইডি, প্রতিটিতে ১০০টি নির্বাচনযোগ্য এন্ট্রি, নিজের গল্প ইনপুট, বাংলা প্রম্পট, আলাদা কপি বাটন, টাইটেল ও ৪টি হ্যাশট্যাগ আছে। এন্ট্রিগুলো আপাতত গল্পের ধারণা/টেমপ্লেট; ৪০০টি পূর্ণাঙ্গ মৌলিক গল্প নয়।
