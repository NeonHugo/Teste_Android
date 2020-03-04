package com.nm.teste_android.ui.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.base.BaseActivity
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.util.extensions.activity.setupToolbarWithTitle
import com.nm.infrastructure.util.extensions.livecycle.bind
import com.nm.teste_android.R
import com.nm.teste_android.databinding.FormActivityBinding
import com.nm.teste_android.ui.simulate.SimulateActivity
import kotlinx.android.synthetic.main.form_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: FormActivityViewModel by viewModel()

    private lateinit var binding: FormActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.form_activity)
        binding.viewModel = viewModel
        initVars()
        initActions()
        subscribeUi()
    }

    private fun initVars() {
        setupToolbarWithTitle(
            binding.toolbar, getString(R.string.form_activity_title), false
        )
    }

    private fun initActions() {
        btn_simulate.setOnClickListener {
            viewModel.onSimulation(
//                et_value.text.toString(),
//                "CDI",
//                et_cdi.text.toString(),
//                "false",
//                formatDate(et_dt_venc.text.toString())
            )
        }
    }

    private fun subscribeUi() {
        bind(viewModel.error, ::showError)
        bind(viewModel.loading, ::showHideLoading)
        bind(viewModel.simulation, ::showSimulation)
        bind(viewModel.enableSimulateButton, ::enableSimulateClick)
    }

    private fun showError(error: Boolean) {

    }

    private fun showHideLoading(loading: Boolean) {

    }

    private fun showSimulation(simulation: Simulate) {
        callSimulate(simulation)
    }

    private fun enableSimulateClick(isEnable: Boolean) {
        binding.formContent.btnSimulate.isEnabled = isEnable
        applyButtonPersonalBackground(isEnable)
    }

    private fun applyButtonPersonalBackground(enable: Boolean) {
        binding.formContent.btnSimulate.backgroundTintList = resources.getColorStateList(
            if (enable) R.color.shamrock else R.color.grayButton,
            null
        )
    }

    private fun callSimulate(simulation: Simulate) {
        val intent = SimulateActivity.newIntent(
            this,
            simulation
        )

        startActivity(intent)

        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FormActivity::class.java)
        }
    }
}
