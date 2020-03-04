package com.nm.teste_android.ui.simulate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.base.BaseActivity
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.util.extensions.activity.setupToolbarWithTitle
import com.nm.infrastructure.util.extensions.format.toCurrency
import com.nm.infrastructure.util.extensions.format.toPercentage
import com.nm.infrastructure.util.extensions.livecycle.bind
import com.nm.teste_android.R
import com.nm.teste_android.ui.form.FormActivity
import kotlinx.android.synthetic.main.simulate_activity.*
import kotlinx.android.synthetic.main.simulate_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SimulateActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: SimulateActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simulate_activity)
        setSupportActionBar(toolbar)

        initVars()
        initActions()
        subscribeUi()
    }

    private fun initVars() {
        setupToolbarWithTitle(
            toolbar, getString(R.string.simulate_activity_title), false
        )

        viewModel.onShowSimulation(
            intent.getParcelableExtra(SIMULATE_KEY)!!
        )
    }

    private fun initActions() {
        btn_simulate_repeat.setOnClickListener {
            callForm()
        }
    }

    private fun subscribeUi() {
        bind(viewModel.error, ::showError)
        bind(viewModel.loading, ::showHideLoading)
        bind(viewModel.simulation, ::showSimulation)
    }

    private fun showError(error: Boolean) {

    }

    private fun showHideLoading(loading: Boolean) {

    }

    private fun showSimulation(simulation: Simulate) {
        tv_gross_amount_main.text = simulation.grossAmountProfit.toCurrency().toString()
        tv_gross_amount_profit_main.text = simulation.grossAmountProfit.toCurrency().toString()
        tv_amount_applied.text = simulation.investmentParameter?.investedAmount.toCurrency().toString()
        tv_gross_amount.text = simulation.grossAmount.toCurrency().toString()
        tv_gross_amount_profit.text = simulation.grossAmountProfit.toCurrency().toString()
        tv_taxes_amount.text = formatTaxes(simulation.taxesAmount, simulation.taxesRate)
        tv_net_amount.text = simulation.netAmount.toCurrency().toString()
        tv_maturity_date.text = formatDate(simulation.investmentParameter?.maturityDate!!)
        tv_maturity_business_days.text =
            simulation.investmentParameter?.maturityBusinessDays.toString()
        tv_monthly_gross_rate_profit.text = simulation.monthlyGrossRateProfit.toPercentage().toString()
        tv_rate.text = simulation.investmentParameter?.rate.toPercentage().toString()
        tv_annual_gross_rate_profit.text = simulation.annualGrossRateProfit.toPercentage().toString()
        tv_rate_profit.text = simulation.rateProfit.toPercentage().toString()
    }

    private fun formatTaxes(taxes: Double, rate: Double): String {
        return "${taxes.toCurrency()} (${rate.toPercentage()})"
    }

    private fun formatDate(date: String): String {
        val formatoE = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
        val formatoS = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val dateE = formatoE.parse(date)
        return formatoS.format(dateE!!)
    }

    override fun onBackPressed() {
        callForm()
    }

    private fun callForm() {
        val intent = FormActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    companion object {
        const val SIMULATE_KEY = "simulate_key"

        fun newIntent(context: Context, simulate: Simulate): Intent {
            return Intent(context, SimulateActivity::class.java).putExtra(SIMULATE_KEY, simulate)
        }
    }
}
