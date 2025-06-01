import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selling.satpudamedical.R

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        val products = listOf(
            Product("Fertisule", "₹ 250", "Reproductive Health", R.drawable.fert_png),
            Product("Petvit", "₹ 400", "Reproductive Renal Health", R.drawable.petvit),
            Product("Petvit", "₹ 120", "Liver Syrup", R.drawable.ic_medicine),
            Product("Petvit", "₹ 500", "Immunity Booster", R.drawable.ic_medicine),
            Product("Fertisule", "₹ 350", "Health Supplement", R.drawable.ic_medicine),
            Product("Petvit", "₹ 180", "Kidney Health", R.drawable.ic_medicine)
        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = ProductAdapter(products)
    }
}
