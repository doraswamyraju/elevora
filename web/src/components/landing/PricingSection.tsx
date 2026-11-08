import React from 'react';

interface PricingSectionProps {
  onSelectPlan: (planName: string) => void;
}

export const PricingSection: React.FC<PricingSectionProps> = ({ onSelectPlan }) => {
  const plans = [
    {
      name: "Basic",
      tagline: "Start your English learning journey.",
      price: "₹99",
      period: "/ month",
      isPopular: false,
      buttonText: "Get Basic",
      features: [
        "Access to core lessons",
        "Practice activities",
        "Progress tracking"
      ]
    },
    {
      name: "AI Learner",
      tagline: "Learn, practice and get AI support.",
      price: "₹199",
      period: "/ month",
      isPopular: true,
      buttonText: "Get AI Learner",
      features: [
        "Everything in Basic",
        "AI Speaking Tutor (limited)",
        "AI feedback & suggestions",
        "Role-play practice"
      ]
    },
    {
      name: "Premium",
      tagline: "Complete learning experience with more AI practice and support.",
      price: "₹299",
      period: "/ month",
      isPopular: false,
      buttonText: "Get Premium",
      features: [
        "Everything in AI Learner",
        "Extended AI conversations",
        "Advanced workplace scenarios",
        "Priority support"
      ]
    }
  ];

  return (
    <section id="pricing" className="py-16 md:py-24 bg-white text-slate-900">
      <div className="max-w-6xl mx-auto px-4 sm:px-6">
        
        {/* Heading */}
        <div className="text-center max-w-xl mx-auto mb-14">
          <h2 className="text-3xl sm:text-4xl font-extrabold text-slate-900 font-display">
            Simple & <span className="text-[#F5A623]">Flexible Pricing</span>
          </h2>
          <p className="mt-2 text-xs sm:text-sm text-slate-500">
            Choose a plan that fits your learning goals.
          </p>
        </div>

        {/* 3 Pricing Cards matching exact borders & yellow Most Popular badge */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 items-stretch">
          {plans.map((p, idx) => (
            <div
              key={idx}
              className={`rounded-3xl p-7 flex flex-col justify-between transition-all relative ${
                p.isPopular
                  ? 'bg-white border-2 border-[#F5A623] shadow-2xl shadow-amber-500/10'
                  : 'bg-white border border-slate-200'
              }`}
            >
              {p.isPopular && (
                <div className="absolute -top-3.5 left-1/2 -translate-x-1/2 px-3 py-1 rounded-full bg-[#F5A623] text-slate-950 font-black text-[10px] tracking-wide uppercase flex items-center gap-1 shadow-sm">
                  <span>★ Most Popular</span>
                </div>
              )}

              <div>
                <h3 className="text-base font-extrabold text-slate-900">{p.name}</h3>
                <p className="text-[11px] text-slate-500 min-h-[30px] mt-0.5">{p.tagline}</p>

                {/* Price Display */}
                <div className="my-4 pb-4 border-b border-slate-100 flex items-baseline gap-1">
                  <span className="text-3xl sm:text-4xl font-black text-slate-900 font-display">
                    {p.price}
                  </span>
                  <span className="text-xs text-slate-500 font-medium">
                    {p.period}
                  </span>
                </div>

                {/* Features List with Yellow Checkmarks */}
                <div className="space-y-2 mb-6">
                  {p.features.map((feat, i) => (
                    <div key={i} className="flex items-center gap-2 text-xs text-slate-600">
                      <span className="text-[#F5A623] font-black text-sm">✓</span>
                      <span>{feat}</span>
                    </div>
                  ))}
                </div>
              </div>

              {/* Card CTA Button (Yellow for AI Learner, Outlined for Basic/Premium) */}
              <button
                onClick={() => onSelectPlan(p.name)}
                className={`w-full py-2.5 rounded-full font-bold text-xs transition-all ${
                  p.isPopular
                    ? 'bg-[#F5A623] hover:bg-[#e0951b] text-slate-950 shadow-md shadow-amber-500/20'
                    : 'bg-white hover:bg-slate-50 text-slate-800 border border-slate-300'
                }`}
              >
                {p.buttonText}
              </button>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
};
