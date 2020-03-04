package com.nm.data.mapper

import com.nm.data.model.InvestmentParameterResponse
import com.nm.domain.entity.InvestmentParameter
import com.nm.infrastructure.net.Mapper

class InvestmentParameterResponseToInvestmentParameterMapper : Mapper<InvestmentParameterResponse, InvestmentParameter>() {

    override fun transform(item: InvestmentParameterResponse?): InvestmentParameter {
        var investmentParameter: InvestmentParameter? = null

        item?.let {
            investmentParameter = InvestmentParameter(
                investedAmount = item.investedAmount,
                yearlyInterestRate = item.yearlyInterestRate,
                maturityBusinessDays = item.maturityBusinessDays,
                maturityTotalDays = item.maturityTotalDays,
                maturityDate = item.maturityDate,
                rate = item.rate,
                isTaxFree = item.isTaxFree
            )
        }

        return investmentParameter ?: InvestmentParameter()
    }

}