class ChatActivity : AppCompatActivity() {
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatRecyclerView: RecyclerView
    private val messages = ArrayList<String>()
    private lateinit var contactNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        contactNumber = intent.getStringExtra("contactNumber") ?: ""

        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener { sendMessage() }
        loadMessages()
    }

    private fun sendMessage() {
        val message = messageInput.text.toString()
        if (message.isNotEmpty()) {
            FirebaseFirestore.getInstance().collection("chats")
                .add(ChatMessage(contactNumber, message, System.currentTimeMillis()))
            messageInput.setText("")
        }
    }

    private fun loadMessages() {
        FirebaseFirestore.getInstance().collection("chats")
            .whereEqualTo("contactNumber", contactNumber)
            .orderBy("timestamp")
            .addSnapshotListener { value, error ->
                if (error != null) return@addSnapshotListener
                messages.clear()
                value?.documents?.forEach { doc ->
                    doc.getString("message")?.let { messages.add(it) }
                }
                chatRecyclerView.adapter = ChatAdapter(messages)
            }
    }
}